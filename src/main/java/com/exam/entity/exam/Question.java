package com.exam.entity.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long queId;
	
	@Column(length = 10000, columnDefinition = "text")
	private String content;
	
	private String image;
	
	@Column(length = 10000, columnDefinition = "text")
	private String option1;
	
	@Column(length = 10000, columnDefinition = "text")
	private String option2;
	
	@Column(length = 10000, columnDefinition = "text")
	private String option3;
	
	@Column(length = 10000, columnDefinition = "text")
	private String option4;
	
	//@Getter(onMethod = @__(@JsonIgnore))
	//@Setter(onMethod = @__(@JsonProperty("answer")))
	@Column(length = 10000, columnDefinition = "text")
	private String answer;
	
	//using transient means this field not stored in database
	@Transient
	private String givenAnswer;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
}
