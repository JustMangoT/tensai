/*
 * This file is part of tensai, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2023 PhoMC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.phomc.tensai.networking.message.c2s;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dev.phomc.tensai.keybinding.Key;
import dev.phomc.tensai.keybinding.KeyState;
import dev.phomc.tensai.networking.Channel;
import dev.phomc.tensai.networking.message.Message;
import dev.phomc.tensai.networking.message.MessageType;

public class KeyBindingStateUpdate extends Message {
	private final List<KeyState> states;

	public KeyBindingStateUpdate() {
		this(new ArrayList<>());
	}

	public KeyBindingStateUpdate(List<KeyState> states) {
		super(Channel.KEYBINDING, MessageType.KEYBINDING_STATE_UPDATE);
		this.states = states;
	}

	public List<KeyState> getStates() {
		return states;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeInt(states.size());

		for (KeyState state : states) {
			stream.writeInt(state.getKey().getCode());
			stream.writeInt(state.getTimesPressed());
		}
	}

	@Override
	public void read(DataInput stream) throws IOException {
		int size = stream.readInt();

		for (int i = 0; i < size; i++) {
			Key key = Key.lookup(stream.readInt());

			if (key != null) {
				states.add(new KeyState(key, stream.readInt()));
			}
		}
	}
}