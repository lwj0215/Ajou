module assign1(
    input logic clk,
    input logic reset,
    input logic d,
    output logic alarm
);
    logic [1:0] state, nextstate;
    logic state_0, state_1;
    logic next_0, next_1;

    assign {state_1, state_0} = state;

    // Next State Logic
    always_comb begin
        next_0 = (~state_1 | ~state_0) & d;
        next_1 = (state_0 | state_1) & d;
        nextstate = {next_1, next_0};
    end

    // State Register
    always_ff @(posedge clk, posedge reset) begin
        if (reset) state <= 0;
        else       state <= nextstate;
    end

    // Output Logic
    assign alarm = state[1];
endmodule