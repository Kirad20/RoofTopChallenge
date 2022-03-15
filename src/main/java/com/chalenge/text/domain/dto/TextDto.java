package com.chalenge.text.domain.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Min;  
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextDto {
  
  private long id;

  @NotBlank
  private String value;

	
  private String hash;

	@Builder.Default
	@NotNull
  private int chars=2;

	@Builder.Default
  private Boolean deleted = Boolean.FALSE;

	@Builder.Default
  private Map<String,Integer> result= new HashMap<>();
}
  