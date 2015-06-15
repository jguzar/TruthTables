Vocabulario:

	Variables: 	De preferencia usar mayúsculas. En caso de usar
			minúsculas hay que cuidar que no sean de las primeras 10, ya
			que utilizo el alfabeto en minúsculas comenzando por la ‘a’
			para sustituir cada paso intermedio en la fórmula.

Conectores válidos ∼,∧,∨,→, y ↔.


Ejemplos de fórmulas:

		P∨∼Q∨(∼P∧Q)
		P∧(P∨Q)→(∼P∧R)
		P∧Q→(∼P→∼R)
		P∧Q∧(∼P→∼Q)
		P∧Q∧(∼P∨∼Q)


Uso: 		java -jar TruthTables.jar “premisa1” “premisa2” “premisaN” “conclusión”

Ejemplo:	java -jar TruthTables.jar "p→(q∨∼r)" "q→(p∧r)" "p→r"
	