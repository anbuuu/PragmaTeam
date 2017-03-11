/**
 * Sample Application provided for Pragma for Code Evaluation by Anbu Subramanian
 */
package com.pragma.domain.exception;

/**
 * Interface to represent a wrapper around an {@link Exception} to manage errors.
 */
public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}
