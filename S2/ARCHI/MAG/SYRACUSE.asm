	LOAD_A #14
syr LOAD_B #1
	AND_B_A
	!si nombre pair
	JMPZ div
	!si nombre impair
	LOAD_B #3
	MUL_A_B
	LOAD_B_A
	INC_B
	LOAD_A_B
	JMP syr
div	LOAD_B_A
	DIV_B #2
	!on stocke le resultat dans A
	LOAD_A_B
	JMP syr