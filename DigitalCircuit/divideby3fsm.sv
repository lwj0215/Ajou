module divideby3FSM(input  logic clk, 
                    input  logic reset, 
                    output logic q);

    typedef enum logic [1:0] {S0, S1, S2, S3} statetype; 
	statetype state, nextstate;  


    // State Register
    always_ff @(posedge clk, posedge reset)
        if (reset) state <= S0;
        else       state <= nextstate;

    // Next State Logic
    always_comb
        case (state)
            S0: nextstate = S1;
            S1: nextstate = S2;
            S2: nextstate = S3;
            S3: nextstate = S0;
            default: nextstate = S0;
        endcase

    // Output Logic
    assign q = (state == S0);
endmodule