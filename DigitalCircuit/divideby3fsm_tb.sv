`timescale 1ps/1ps

module divideby3FSM_tb();
    logic clk;
    logic reset; 
    logic q;

    divideby3FSM dut(clk, reset, q);

    // generate clock
    always begin
        clk = 1; #1; 
        clk = 0; #1;
    end

    initial begin
        $dumpfile("divideby3FSM_tb.vcd");
        $dumpvars(0, divideby3FSM_tb);

        reset = 1; #1;
        reset = 0; #30;
        reset = 1;

        $display("Test Complete");
        #10;
        $finish(1);
    end

endmodule