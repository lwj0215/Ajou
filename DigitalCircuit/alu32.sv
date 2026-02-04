module alu32( input logic [31:0] A, B,
    input logic [2:0] F,
    output logic [31:0] Y);

always_comb begin
    case(F)
        3'b000: Y = A & B;
        3'b001: Y = A | B;
        3'b010: Y = A + B;
        3'b100: Y = A & ~B;
        3'b101: Y = A | ~B;
        3'b110: Y = A - B;
        3'b111: Y = (A<B)? 32'b1 : 32'b0;
    endcase
end


endmodule