`timescale 1ps/1ps

module sevenseg_tb();
    logic [3:0] data; 
    logic [6:0] segments;

    sevenseg dut(data, segments);

    initial begin
        $dumpfile("sevenseg_tb.vcd");
        $dumpvars(0, sevenseg_tb);

        data = 4'd0; #3;
        data = 4'd1; #3;
        data = 4'd2; #3;
        data = 4'd3; #3;
        data = 4'd4; #3;
        data = 4'd5; #3;
        data = 4'd6; #3;
        data = 4'd7; #3;
        data = 4'd8; #3;
        data = 4'd9; #3;

        $display("Test Complete");
        #10;
        $finish(1);
    end

endmodule