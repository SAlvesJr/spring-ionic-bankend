package com.SAlvesjr.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.SAlvesjr.cursomc.domain.Cidade;
import com.SAlvesjr.cursomc.domain.Cliente;
import com.SAlvesjr.cursomc.domain.Endereco;
import com.SAlvesjr.cursomc.domain.dto.ClienteDTO;
import com.SAlvesjr.cursomc.domain.dto.ClienteNewDTO;
import com.SAlvesjr.cursomc.domain.enums.Perfil;
import com.SAlvesjr.cursomc.domain.enums.TipoCliente;
import com.SAlvesjr.cursomc.repositories.ClienteRepository;
import com.SAlvesjr.cursomc.repositories.EnderecoRepository;
import com.SAlvesjr.cursomc.security.UserSS;
import com.SAlvesjr.cursomc.services.exception.AuthorizationException;
import com.SAlvesjr.cursomc.services.exception.DataIntegrityException;
import com.SAlvesjr.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder dCruypt;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;

	public Cliente findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente cli) {
		cli.setId(null);
		enderecoRepository.saveAll(cli.getEnderecos());
		return clienteRepository.save(cli);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(obj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Long id) {
		findById(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir o cliente com pedidos");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso Negado");
		}

		Cliente cli = clienteRepository.findByEmail(email);
		if (cli == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado, Id: " + user.getId() + " Tipo: " + Cliente.class.getName());
		}
		return cli;
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO obj, Long id) {
		return new Cliente(id, obj.getNome(), obj.getEmail(), null, null, null);
	}

	public Cliente fromDTO(@Valid ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objDTO.getTipo()), dCruypt.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone_1());
		if (objDTO.getTelefone_2() != null) {
			cli.getTelefones().add(objDTO.getTelefone_2());
		}
		if (objDTO.getTelefone_3() != null) {
			cli.getTelefones().add(objDTO.getTelefone_3());
		}
		return cli;
	}

	public URI uploadProfileFile(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "imagem");
	}

}
