package es.curso.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.curso.modelo.entidad.Usuario;
import es.curso.modelo.entidad.Videojuego;
import es.curso.modelo.negocio.GestorUsuarios;
import es.curso.modelo.negocio.GestorVideojuego;

@Controller
public class Controlador {

	@RequestMapping("verlogin")
	public String verlogin() {
		return "login";
	}
	
	
	GestorVideojuego gv = new GestorVideojuego();
	
	@PostMapping("listaJuegos")
	public String verListaJuegos(@RequestParam("user") String user,
								 @RequestParam("pass") String pass,
								 HttpServletRequest r){
		GestorUsuarios gu = new GestorUsuarios();
		
		
		Usuario u = gu.verificar(user, pass);
		List<Videojuego> listaJuegos = gv.listar();
		
		r.setAttribute("listado", listaJuegos);
		
		if(u != null)  {			
			return "listaVideojuegos";
		}else return "errorLista";
		
	}
	
	@PostMapping("detalle")
	public String verDetalle(@RequestParam("id") String id, HttpServletRequest r){
		int newId = Integer.parseInt(id);
		Videojuego j = gv.obtener(newId);
		
		if(j != null)  {
			r.setAttribute("j", j);
			return "detalleJuego";
		}else return "errorDetalle";
	}
	
	
}


