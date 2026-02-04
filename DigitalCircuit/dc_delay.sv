//iverilog -g2012 -o out_d.o dc_delay.sv dc_delay_tb.sv
//vvp out_d
//getkwave eg_tb.vcd
`timescale 1ps/1ps
module example(input  logic a, b, c,
               output logic y);

  logic ab, bb, cb, n1, n2, n3;

  assign #1 {ab, bb, cb} = ~{a, b, c};
  assign #2 n1 = ab & bb & cb;
  assign #2 n2 = a | bb | cb;
  assign #2 n3 = a & bb & c;
  assign #4 y = n1 | n2 | n3;
endmodule