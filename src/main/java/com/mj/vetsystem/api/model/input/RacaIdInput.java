package com.mj.vetsystem.api.model.input;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RacaIdInput {

	@NotNull
	private Long id;
	
}