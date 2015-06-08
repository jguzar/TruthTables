Vocabulario:

	Variables: 	De preferencia usar mayúsculas. En caso de usar
			minúsculas hay que cuidar que no sean de las primeras 10, ya
			que utilizo el alfabeto en minúsculas comenzando por la ‘a’
			para sustituir cada paso intermedio en la fórmula.

		| 	= v 	(OR)
		& 	= ^ 	(AND)
		> 	= -> 	(CONDICIONAL)
		-	= ~	(NEGACIÓN)


Ejemplos de fórmulas:

		P|-Q|(-P&Q)
		P&(P|Q)>(-P&R)
		P&Q>(-P>-R)
		P&Q&(-P>-Q)
		P&Q&(-P|-Q)


Uso: 		java -jar TruthTables.jar “formula”

Ejemplo:	java -jar TruthTables.jar "P&Q&(-P|-Q)"
	