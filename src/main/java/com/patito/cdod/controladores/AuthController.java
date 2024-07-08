package com.patito.cdod.controladores;

import java.security.Principal;

import javax.validation.Valid;

import com.patito.cdod.dto.ClientePayload;
import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.entities.Cliente;
import com.patito.cdod.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(allowCredentials = "true", origins="http://localhost:5173")
public class AuthController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/registrarse")
	public CustomResponseEntity<Cliente> registerUser(@Valid @RequestBody ClientePayload user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return clienteService.create(user);
	}

	@GetMapping("/prueba")
	public String prueba() {
		//userService.pruebaMail();
		return "nada";
	}
	
	/*@GetMapping(value = "/confirm", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	@ApiOperation(value = "confirma el email para habilitar el usuario", notes = "Habilida el Usuario" )
	public String confirmEmail(@RequestParam String code) {
		userService.confirmEmail(code);
		return "<html>\n" + "<header><title>Correo Confirmado</title></header>\n" +
		          "<body>\n <div style='width: 100%; text-align: center;'>" + "El correo ha sido confirmado\n" + "</div></body>\n" + "</html>";
	}*/

	@PutMapping("/update")
	@ResponseBody
	public CustomResponseEntity<Cliente> actualizarUsuario(@Valid @RequestBody ClientePayload newUser, Principal principal) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		return clienteService.actualizar(newUser, principal.getName());
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public CustomResponseEntity<String> borrarUsuario(Principal principal) {
		return clienteService.borrar(principal.getName());
	}
}
