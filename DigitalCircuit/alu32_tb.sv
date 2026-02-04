`timescale 1ps/1ps

module alu32_tb();
    logic clk;
    logic [31:0] A, B;
    logic [2:0] F;
    logic [31:0] Y;

    alu32 dut(A,B,F,Y);

    always begin
        clk = 1; #5; 
        clk = 0; #5;
    end

    initial begin
        $dumpfile("alu32_tb.vcd");
        $dumpvars(0, alu32_tb);

        A = 32'd64;
        B = 32'd128;
        F = 3'b000; #10;
        F = 3'b001; #10;
        F = 3'b010; #10;   
        F = 3'b100; #10;   
        F = 3'b101; #10;   
        F = 3'b110; #10;   
        F = 3'b111; #10;    
        
        $display("Test Complete");
        #10;
        $finish(1);
    end

endmodule