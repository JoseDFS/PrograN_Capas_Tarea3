package com.uca.capas.tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/ingresar")
	public String index() {
		return "/index";
	}
	
	@RequestMapping("/parametros")
	public ModelAndView parametros(@RequestParam(value="nombres") String nombres, 
			@RequestParam(value="apellidos") String apellidos,@RequestParam(value="fecha_N") String fecha,
			@RequestParam(value="lugar_N") String lugar_N,@RequestParam(value="instituto") String instituto,
			@RequestParam(value="tel_F") String tel_F,@RequestParam(value="tel_M") String tel_M) {
		
		List<String> lista = new ArrayList<>();
		ModelAndView mav = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date minDate = null;
		Date fecha_N = null;
		
		try {
			fecha_N = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		try {
			minDate = sdf.parse("2003-01-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(nombres.length()<1 || nombres.length()>25)
			lista.add("Nombres tiene que tener por lo menos un caracter y no mas de 25");
		if(apellidos.length()<1 || apellidos.length()>25)
			lista.add("Apellidos tiene que tener por lo menos un caracter y no mas de 25");
		if(fecha_N.compareTo(minDate)<0)
			lista.add("Fecha de Nacimiento no puede ser menor al 1 de enero de 2003");
		if(lugar_N.length()<1 || lugar_N.length()>25)
			lista.add("Lugar de Nacimiento tiene que tener por lo menos un caracter y no mas de 25");
		if(instituto.length()<1 || instituto.length()>100)
			lista.add("Instituto o Colegio tiene que tener por lo menos un caracter y no mas de 100");
		if(tel_F.length()!=8)
			lista.add("El Telefono Fijo tiene que contener 8 numeros");
		if(tel_M.length()!=8)
			lista.add("El Telefono Movil tiene que contener 8 numeros");
		
		if(lista.size()>0) {
			mav.addObject("errores", lista);
			mav.setViewName("/errores");
			System.out.println(lista);
			return mav;
		}
		mav.setViewName("/exito");
		return mav;
	}

}
