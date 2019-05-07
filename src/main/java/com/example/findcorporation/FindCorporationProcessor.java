package com.example.findcorporation;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.StringUtils;

@EnableBinding(Processor.class)
public class FindCorporationProcessor {

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public DownloadInfo handle(String info) throws Exception {
		System.out.println("Incoming Data::: " + info);

		String[] split = info.split(",");

		if (split != null && !StringUtils.isEmpty(split[0]) && !trim(split[0]).equals("ip_address")) {

			DownloadInfo readValue = new DownloadInfo();
			readValue.setIpAddress(trim(split[0]));
			readValue.setHostname(StringUtils.isEmpty(split[1]) ? getHostName(readValue.getIpAddress()) : trim(split[1]));
			readValue.setOrganization(trim(split[2]));
			readValue.setCountry(trim(split[3]));
			readValue.setZipcode(trim(split[5]));
			readValue.setUsername(trim(split[6]));
			readValue.setDate(trim(split[8]));

			readValue.setPathInformation(trim(split[10]));
			String[] pathSplit = readValue.getPathInformation().split("/");
			readValue.setReleaseNumber((pathSplit != null && pathSplit.length > 7) ? trim(pathSplit[7]) : "Don't know");

			readValue.setUserAgent(trim(split[14]));

			System.out.println("Processed Data::: " + readValue);
			return readValue;
		}

		System.out.println("Header Data::: " + split[0]);
		return null;
	}

	private static String getHostName(final String ip) {
		String retVal = null;
		final String[] bytes = ip.split("\\.");
		if (bytes.length == 4) {
			try {
				final java.util.Hashtable<String, String> env = new java.util.Hashtable<String, String>();
				env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
				final javax.naming.directory.DirContext ctx = new javax.naming.directory.InitialDirContext(env);
				final String reverseDnsDomain =
						bytes[3] + "." + bytes[2] + "." + bytes[1] + "." + bytes[0] + ".in-addr.arpa";
				final javax.naming.directory.Attributes attrs = ctx.getAttributes(reverseDnsDomain, new String[]
						{
								"PTR",
						});
				for (final javax.naming.NamingEnumeration<? extends javax.naming.directory.Attribute> ae = attrs
						.getAll(); ae.hasMoreElements(); ) {
					final javax.naming.directory.Attribute attr = ae.next();
					final String attrId = attr.getID();
					for (final java.util.Enumeration<?> vals = attr.getAll(); vals.hasMoreElements(); ) {
						String value = vals.nextElement().toString();
						// System.out.println(attrId + ": " + value);

						if ("PTR".equals(attrId)) {
							final int len = value.length();
							if (value.charAt(len - 1) == '.') {
								// Strip out trailing period
								value = value.substring(0, len - 1);
							}
							retVal = value;
						}
					}
				}
				ctx.close();
			}
			catch (final javax.naming.NamingException e) {
				// No reverse DNS that we could find, try with InetAddress
				System.out.print(""); // NO-OP
			}
		}

		if (null == retVal) {
			try {
				retVal = java.net.InetAddress.getByName(ip).getCanonicalHostName();
			}
			catch (final java.net.UnknownHostException e1) {
				retVal = ip;
			}
		}

		return retVal;
	}

	static String trim(String text) {
		return text.replaceAll("^(['\"])(.*)\\1$", "$2");
	}
}

