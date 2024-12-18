package com.restproject.coverage;

import org.jacoco.core.data.ExecutionDataWriter;
import org.jacoco.core.runtime.RemoteControlReader;
import org.jacoco.core.runtime.RemoteControlWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This example connects to a coverage agent that run in output mode
 * <code>tcpserver</code> and requests execution data. The collected data is
 * dumped to a local file.
 */
public final class ExecutionDataClient {

	private static final String DESTFILE = "jacoco-client.exec";

	private static final String ADDRESS = "localhost";

	private static final int PORT = 6300;

	/**
	 * Starts the execution data request.
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {
		final FileOutputStream localFile = new FileOutputStream(DESTFILE);
		final ExecutionDataWriter localWriter = new ExecutionDataWriter(
			localFile);

		// Open a socket to the coverage agent:
		final Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
		final RemoteControlWriter writer = new RemoteControlWriter(
			socket.getOutputStream());
		final RemoteControlReader reader = new RemoteControlReader(
			socket.getInputStream());
		reader.setSessionInfoVisitor(localWriter);
		reader.setExecutionDataVisitor(localWriter);
		//reader.setRemoteCommandVisitor(writer);
		// Send a dump command and read the response:
		//localWriter.flush();
		writer.visitDumpCommand(true, false);
		if (!reader.read()) {
			throw new IOException("Socket closed unexpectedly.");
		}

		socket.close();
		localFile.close();
	}

	private ExecutionDataClient() {
	}
}
