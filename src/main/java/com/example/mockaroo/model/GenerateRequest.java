package com.example.mockaroo.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenerateRequest {

	private int rows;
	private List<SchemaField> fields;
	private String format;
}
