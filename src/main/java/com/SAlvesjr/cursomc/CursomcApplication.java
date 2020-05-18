package com.SAlvesjr.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.SAlvesjr.cursomc.domain.Categoria;
import com.SAlvesjr.cursomc.domain.Cidade;
import com.SAlvesjr.cursomc.domain.Cliente;
import com.SAlvesjr.cursomc.domain.Endereco;
import com.SAlvesjr.cursomc.domain.Estado;
import com.SAlvesjr.cursomc.domain.ItemPedido;
import com.SAlvesjr.cursomc.domain.Pagamento;
import com.SAlvesjr.cursomc.domain.PagamentoBoleto;
import com.SAlvesjr.cursomc.domain.PagamentoCartao;
import com.SAlvesjr.cursomc.domain.Pedido;
import com.SAlvesjr.cursomc.domain.Produto;
import com.SAlvesjr.cursomc.domain.enums.EstadoPagamento;
import com.SAlvesjr.cursomc.domain.enums.TipoCliente;
import com.SAlvesjr.cursomc.repositories.CategoriaRepository;
import com.SAlvesjr.cursomc.repositories.CidadeRepository;
import com.SAlvesjr.cursomc.repositories.ClienteRepository;
import com.SAlvesjr.cursomc.repositories.EnderecoRepository;
import com.SAlvesjr.cursomc.repositories.EstadoRepository;
import com.SAlvesjr.cursomc.repositories.ItemPedidoRepository;
import com.SAlvesjr.cursomc.repositories.PagamentoRepository;
import com.SAlvesjr.cursomc.repositories.PedidoRepository;
import com.SAlvesjr.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressaora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cl1 = new Cliente(null, "Maria", "maria@email.com", "11613377096", TipoCliente.PESSOAFISICA);

		cl1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cl1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cl1, c2);

		cl1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cl1));

		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/90/2017 10:32"), cl1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, e2);
		
		Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null );
		ped2.setPagamento(pag2);
		
		cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		ItemPedido it1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido it2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido it3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(it1, it3));
		ped2.getItens().addAll(Arrays.asList(it3));
		
		p1.getItens().addAll(Arrays.asList(it1));
		p2.getItens().addAll(Arrays.asList(it3));
		p3.getItens().addAll(Arrays.asList(it2));
		
		itemPedidoRepository.saveAll(Arrays.asList(it1, it2, it3));
	}

}
