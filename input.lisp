; Find the derivative of polynomial poly with repect to variable var.
; The polynomial must be in canonical infix form.
(define deriv
  (lambda (poly var)
    (let* ((terms (terminize poly)) ; "terminize" the polynomial
           (deriv-term              ; local procedure deriv-term 
             (lambda (term)
               (cond
                 ((null? term) '())
                 ((not (member? var term)) '(0))           ; deriv = 0
                 ((not (member? '^ term)) (upto var term)) ; deriv = coeff
                 (else (deriv-term-expo term var))         ; handle exponent
             )))
           (diff (map deriv-term terms)))   ; map deriv-term over the terms
      (remove-trailing-plus (polyize diff)) ; finalize the answer
)))

; Convert an infix polynomial into a list of sublists,
; where each sublist is a term.
(define terminize
  (lambda (poly)
    (cond
      ((null? poly) '())
      (else (cons (upto '+ poly) (terminize (after '+ poly))))
)))
