package be.pxl.paj.budgetplanner.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("ping")
	public ResponseEntity<String> demo() {
		return ResponseEntity.ok().body("Hello world!");
	}
}
