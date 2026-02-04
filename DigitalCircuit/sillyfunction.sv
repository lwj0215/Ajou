//iverilog -g2012 -o sf.o sillyfunction.sv sillyfunction_testbench.sv
//vvp sf.o
module sillyfunction(input  logic a, b, c, output logic y);

  assign y = ~a & ~b & ~c | a & ~b & ~c | a & ~b &  c; //continuous assignment statement
endmodule
