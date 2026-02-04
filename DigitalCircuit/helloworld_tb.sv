//iverilog -g2012 -o run.vvp helloworld.sv
//vvp run.vvp
module helloworld;
    initial begin
        $display("Hello Verilog");
    end
endmodule