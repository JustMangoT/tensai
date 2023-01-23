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

package dev.phomc.tensai.keybinding;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a key state.<br>
 * <b>The key state is used on server-side only. No key state update will be sent to client.</b>
 */
public class KeyState {
	private final Key key;
	private int timesPressed;

	public KeyState(@NotNull Key key, int timesPressed) {
		this.key = key;
		this.timesPressed = timesPressed;
	}

	@NotNull
	public Key getKey() {
		return key;
	}

	public int getTimesPressed() {
		return timesPressed;
	}

	public boolean wasPressed() {
		if (timesPressed == 0) {
			return false;
		}

		timesPressed--;
		return true;
	}

	public void reset() {
		timesPressed = 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		KeyState keyState = (KeyState) o;
		return timesPressed == keyState.timesPressed && key == keyState.key;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, timesPressed);
	}
}