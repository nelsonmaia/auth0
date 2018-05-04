package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

@RestController
public class GreetingController {

	private static final String template = "%s!";
	private final AtomicLong counter = new AtomicLong();

	@PostMapping("/greeting")
	public Greeting greeting(@RequestParam("name") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@PutMapping("/greeting")
	public Greeting changeGreeting(@RequestParam("name") String name, @RequestParam(name = "newGreeting") String newGreeting) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name), newGreeting);
	}
	
	@DeleteMapping("/greeting")
	public Greeting deleteGreeting(@RequestParam(name = "name") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name), null);
	}

}
