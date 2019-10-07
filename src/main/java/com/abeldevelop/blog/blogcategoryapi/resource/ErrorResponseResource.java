package com.abeldevelop.blog.blogcategoryapi.resource;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "Resource that contains the information of an error")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponseResource {

	@ApiModelProperty(notes = "Time when error occurs", required = true, position = 0, example = "2019-02-09T20:35:37.384")
	@NotNull
    private LocalDateTime timestamp;
    
    @ApiModelProperty(notes = "Error status code", required = true, position = 1, example = "500")
    @NotNull
    private Integer status;
    
    @ApiModelProperty(notes = "Error name", required = true, position = 2, example = "Internal Server Error")
    @NotNull
    private String error;
    
    @ApiModelProperty(notes = "Error message", required = true, position = 3, example = "User not found")
    @NotNull
    private String message;
    
    @ApiModelProperty(notes = "Error details (Internal Usage)", required = false, position = 4, example = "java.lang.NullPointerException")
    @JsonInclude(Include.NON_NULL)
    private String detail;
    
    @ApiModelProperty(notes = "Steps where occurs the error (Internal Usage)", required = false, position = 5)
    @JsonInclude(Include.NON_NULL)
    private List<String> stackTrace;
    
    @ApiModelProperty(notes = "Cause why error occurs (Internal Usage)", required = false, position = 6)
    private ErrorResponseResource cause;
    
}
