import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class _05_RestoreIPAddresses {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses2("0000"));
    }

    public static List<String> restoreIpAddresses2(String s) {
        List<String> ips = new ArrayList<>();
        split(ips, s, "", 0);
        return ips;
    }

    public static void split(List<String> ips, String s, String before, int part) {
        if (part == 3) {
            if (validIpPart(s)) {
                ips.add(before + s);
                return;
            }
        }
        if (part < 3) {
            for (int i = 1; i <= 3 && i < s.length(); i++) {
                String partString = s.substring(0, i);
                if (validIpPart(partString)) {
                    split(ips, s.substring(i), before + partString + ".", part + 1);
                }
            }
        }
    }


    public static List<String> restoreIpAddresses(String s) {
        List<String> ips = new ArrayList<>();
        if (s.length() > 12) {
            return ips;
        }
        for (int i = 0; i < s.length(); i++) {
            String part1 = s.substring(0, i);
            if (!validIpPart(part1)) {
                continue;
            }
            for (int j = i + 1; j < s.length(); j++) {
                String part2 = s.substring(i, j);
                if (!validIpPart(part2)) {
                    continue;
                }
                for (int k = j + 1; k < s.length(); k++) {
                    String part3 = s.substring(j, k);
                    String part4 = s.substring(k, s.length());
                    if (validIpPart(part3) && validIpPart(part4)) {
                        ips.add(part1 + "." + part2 + "." + part3 + "." + part4);
                    }
                }
            }
        }
        return ips;
    }

    public static boolean validIpPart(String ipPartStr) {
        if (ipPartStr.length() == 0 || ipPartStr.length() > 3) {
            return false;
        }
        int ipPart = Integer.valueOf(ipPartStr);
        return (String.valueOf(ipPart).length() == ipPartStr.length()) && ipPart < 256;
    }
}
