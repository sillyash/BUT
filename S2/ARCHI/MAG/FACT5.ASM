	LOAD_A #5
	LOAD_B_A
dec DEC_B
	MUL_A_B
	DEC_B
	JMPNZ dec
	NOP