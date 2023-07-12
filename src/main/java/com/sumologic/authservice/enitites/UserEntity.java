package com.sumologic.authservice.enitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sumologic.authservice.constant.ValidationConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

	@Column(length = 50)
	@NotNull(message = ValidationConstant.NAME_NOT_NULL)
	@NotEmpty(message = ValidationConstant.NAME_NOT_EMPTY)
	@Size(min = 3, message = ValidationConstant.NAME_SIZE_VALIDATION)
	private String name;

	@Column(nullable = false, length = 200)
	@NotNull(message = ValidationConstant.PASSWORD_NOT_NULL)
	@NotEmpty(message = ValidationConstant.PASSWORD_NOT_EMPTY)
	private String password;

	@Column(nullable = false, length = 30, unique = true)
	@NotNull(message = ValidationConstant.EMAIL_NOT_EMPTY)
	@NotEmpty(message = ValidationConstant.EMAIL_NOT_EMPTY)
	@Size(min = 4, message = ValidationConstant.EMAIL_SIZE_VALIDATION)
	@Email
	private String email;

}
