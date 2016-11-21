/*
 * Copyright 2016 Martin Winandy
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.tinylog.pattern;

import java.util.Collection;
import java.util.EnumSet;

import org.tinylog.backend.LogEntry;
import org.tinylog.backend.LogEntryValue;

/**
 * Token for outputting the text message and the exception or other throwable of a log entry.
 */
final class MessageAndExceptionToken implements Token {

	private ExceptionToken exceptionToken;

	/** */
	MessageAndExceptionToken() {
		this.exceptionToken = new ExceptionToken();
	}

	@Override
	public Collection<LogEntryValue> getRequiredLogEntryValues() {
		return EnumSet.of(LogEntryValue.MESSAGE, LogEntryValue.EXCEPTION);
	}

	@Override
	public void render(final LogEntry logEntry, final StringBuilder builder) {
		String message = logEntry.getMessage();
		if (message != null) {
			builder.append(message);
		}

		if (logEntry.getException() != null) {
			if (message != null) {
				builder.append(": ");
			}
			exceptionToken.render(logEntry, builder);
		}
	}

}