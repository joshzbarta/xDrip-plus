package com.eveningoutpost.dexdrip.g5models;

// jamorham

public class BondRequestTxMessage extends BaseMessage {
    static final byte opcode = 0x07;

    public BondRequestTxMessage() {
        init(opcode, 1);
    }
}

