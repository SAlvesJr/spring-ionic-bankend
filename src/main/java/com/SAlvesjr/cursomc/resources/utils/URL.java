package com.SAlvesjr.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Long> decodeLongList(String s) {
		if(s.isEmpty()) {
			return new ArrayList<Long>();
		}
		String[] vet = s.split(",");
		List<Long> list = new ArrayList<>();
		for (String string : vet) {
			list.add(Long.parseLong(string));
		}
		// Arrays.asList(s.split(",")).stream().map(x ->
		// Long.parseLong(x)).collect(Collectors.toList());
		return list;

	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
