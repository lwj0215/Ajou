
module flopr_a( input  logic clk,
                input  logic reset, 
                input  logic d, 
                output logic q);
   
  // asynchronous reset
  always_ff @(posedge clk, posedge reset)
    if (reset) q <= 1'b0;
    else       q <= d;
endmodule
