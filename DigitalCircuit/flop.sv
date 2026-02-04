// iverilog -g2012 -o out.o flop.sv flop_tb.sv
// vvp out.o
// gtkwave flop_tb.vcd
module flop(input  logic clk, 
            input  logic d, 
            output logic q);

  always_ff @(posedge clk)
    q <= d;
endmodule