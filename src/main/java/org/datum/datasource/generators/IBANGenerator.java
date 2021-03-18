package org.datum.datasource.generators;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.datum.model.CharsGroup;
import org.datum.model.IBANRecord;

import static org.datum.model.CharType.*;
import static org.datum.model.CharsGroup.*;

/**
 * 
 * IBAN codes generator
 * 
 * Supports official IBAN 66 countries of which 34 are SEPA members.
 * 
 * System generates identify data structures and perform format, check digit and
 * length validations
 * 
 * The original sources:
 * 
 * https://www.iban.com/structure
 * 
 * https://en.wikipedia.org/wiki/International_Bank_Account_Number
 * 
 * Legend:
 * 
 * b = National bank code / BIC bank code
 * <p/>
 * s = Branch code
 * <p/>
 * x = National check digit
 * <p/>
 * c = Account number
 * <p/>
 * a = Balance account number
 * <p/>
 * t = Account type (cheque account, savings account etc.)
 * <p/>
 * n = Owner account number ("1", "2"etc.)
 * <p/>
 * i = Account holder's kennitala (national identification number)
 * <p/>
 * 
 * The kk after the two-character ISO country code represents the check digits
 * calculated from the rest of the IBAN characters. If it is a constant for the
 * country concerned, this will be stated in the Comments column. This happens
 * where the BBAN has its own check digits that use the same algorithm as the
 * IBAN check digits
 * 
 * The BBAN format column shows the format of the BBAN part of an IBAN in terms
 * of:
 * 
 * upper case alpha characters (A–Z) denoted by "a",
 * 
 * numeric characters (0–9) denoted by "n"and
 * 
 * mixed case alphanumeric characters (a–z, A–Z, 0–9) denoted by "c".
 * 
 * For example, the Bulgarian BBAN (4a,6n,8ALPHA_NUMERIC) consists of 4 alpha
 * characters, followed by 6 numeric characters, then by 8 mixed-case
 * alpha-numeric characters
 * 
 * 
 * 
 * 
 * 
 * @author akaliutau
 *
 */
public class IBANGenerator {

	private static final List<IBANRecord> accounts = new ArrayList<>();
	private static Map<String, IBANRecord> ibanMap = new HashMap<>();

	static {

		accounts.add(new IBANRecord("Albania", 28, "AL", "ALkk bbbs sssx cccc cccc cccc cccc", of(8, DIGITS),
				of(16, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Andorra", 24, "AD", "ADkk bbbb ssss cccc cccc cccc", of(8, DIGITS),
				of(12, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Austria", 20, "AT", "ATkk bbbb bccc cccc cccc", of(16, DIGITS)));
		accounts.add(new IBANRecord("Azerbaijan", 28, "AZ", "AZkk bbbb cccc cccc cccc cccc cccc", of(4, ALPHA_NUMERIC),
				of(20, DIGITS)));
		accounts.add(new IBANRecord("Bahrain", 22, "BH", "BHkk bbbb cccc cccc cccc cc", of(4, LETTERS),
				of(14, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Belarus", 28, "BY", "BYkk bbbb aaaa cccc cccc cccc cccc", of(4, ALPHA_NUMERIC),
				of(4, DIGITS), of(16, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Belgium", 16, "BE", "BEkk bbbc cccc ccxx", of(12, DIGITS)));
		accounts.add(
				new IBANRecord("Bosnia and Herzegovina", 20, "BA", "BA39 bbbs sscc cccc ccxx", 39, of(16, DIGITS)));
		accounts.add(new IBANRecord("Brazil", 29, "BR", "BRkk bbbb bbbb ssss sccc cccc ccct n", of(23, DIGITS),
				of(1, LETTERS), of(1, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Bulgaria", 22, "BG", "BGkk bbbb ssss ttcc cccc cc", of(4, LETTERS), of(6, DIGITS),
				of(8, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Costa Rica", 22, "CR", "CRkk nnbb cccc cccc cccc cc", of(18, DIGITS)));
		accounts.add(new IBANRecord("Croatia", 21, "HR", "HRkk bbbb bbbc cccc cccc c", of(17, DIGITS)));
		accounts.add(new IBANRecord("Cyprus", 28, "CY", "CYkk bbbs ssss cccc cccc cccc cccc", of(8, DIGITS),
				of(16, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Czech Republic", 24, "CZ", "CZkk bbbb ssss sscc cccc cccc", of(20, DIGITS)));
		accounts.add(new IBANRecord("Denmark", 18, "DK", "DKkk bbbb cccc cccc cc", of(14, DIGITS)));
		accounts.add(new IBANRecord("Dominican Republic", 28, "DO", "DOkk bbbb cccc cccc cccc cccc cccc",
				of(4, LETTERS), of(20, DIGITS)));
		accounts.add(new IBANRecord("East Timor", 23, "TL", "TL38 bbbc cccc cccc cccc cxx", 38, of(19, DIGITS)));
		accounts.add(new IBANRecord("Egypt", 29, "EG", "EGkk bbbb ssss cccc cccc cccc cccc c", of(25, DIGITS)));
		accounts.add(new IBANRecord("El Salvador", 28, "SV", "SVkk bbbb cccc cccc cccc cccc cccc", of(4, LETTERS),
				of(20, DIGITS)));
		accounts.add(new IBANRecord("Estonia", 20, "EE", "EEkk bbss cccc cccc cccx", of(16, DIGITS)));
		accounts.add(new IBANRecord("Faroe Islands", 18, "FO", "FOkk bbbb cccc cccc cx", of(14, DIGITS)));
		accounts.add(new IBANRecord("Finland", 18, "FI", "FIkk bbbb bbcc cccc cx", of(14, DIGITS)));
		accounts.add(new IBANRecord("France", 27, "FR", "FRkk bbbb bsss sscc cccc cccc cxx", of(10, DIGITS),
				of(11, ALPHA_NUMERIC), of(2, DIGITS)));
		accounts.add(new IBANRecord("Georgia", 22, "GE", "GEkk bbcc cccc cccc cccc cc", of(2, ALPHA_NUMERIC),
				of(16, DIGITS)));
		accounts.add(new IBANRecord("Germany", 22, "DE", "DEkk bbbb bbbb cccc cccc cc", of(18, DIGITS)));
		accounts.add(new IBANRecord("Gibraltar", 23, "GI", "GIkk bbbb cccc cccc cccc ccc", of(4, LETTERS),
				of(15, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Greece", 27, "GR", "GRkk bbbs sssc cccc cccc cccc ccc", of(7, DIGITS),
				of(16, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Greenland", 18, "GL", "GLkk bbbb cccc cccc cc", of(14, DIGITS)));
		accounts.add(new IBANRecord("Guatemala", 28, "GT", "GTkk bbbb mmtt cccc cccc cccc cccc", of(4, ALPHA_NUMERIC),
				of(20, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Hungary", 28, "HU", "HUkk bbbs sssx cccc cccc cccc cccx", of(24, DIGITS)));
		accounts.add(new IBANRecord("Iceland", 26, "IS", "ISkk bbss ttcc cccc iiii iiii ii", of(22, DIGITS)));
		accounts.add(new IBANRecord("Iraq", 23, "IQ", "IQkk bbbb sssc cccc cccc ccc", of(4, LETTERS), of(15, DIGITS)));
		accounts.add(new IBANRecord("Ireland", 22, "IE", "IEkk aaaa bbbb bbcc cccc cc", of(4, ALPHA_NUMERIC),
				of(14, DIGITS)));
		accounts.add(new IBANRecord("Israel", 23, "IL", "ILkk bbbs sscc cccc cccc ccc", of(19, DIGITS)));
		accounts.add(new IBANRecord("Italy", 27, "IT", "ITkk xbbb bbss sssc cccc cccc ccc", of(1, LETTERS),
				of(10, DIGITS), of(12, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Jordan", 30, "JO", "JOkk bbbb ssss cccc cccc cccc cccc cc", of(4, LETTERS),
				of(22, DIGITS)));
		accounts.add(new IBANRecord("Kazakhstan", 20, "KZ", "KZkk bbbc cccc cccc cccc", of(3, DIGITS),
				of(13, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Kosovo", 20, "XK", "XKkk bbbb cccc cccc cccc", of(4, DIGITS), of(10, DIGITS),
				of(2, DIGITS)));
		accounts.add(new IBANRecord("Kuwait", 30, "KW", "KWkk bbbb cccc cccc cccc cccc cccc cc", of(4, LETTERS),
				of(22, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Latvia", 21, "LV", "LVkk bbbb cccc cccc cccc c", of(4, LETTERS),
				of(13, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Lebanon", 28, "LB", "LBkk bbbb cccc cccc cccc cccc cccc", of(4, DIGITS),
				of(20, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Liechtenstein", 21, "LI", "LIkk bbbb bccc cccc cccc c", of(5, DIGITS),
				of(12, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Lithuania", 20, "LT", "LTkk bbbb bccc cccc cccc", of(16, DIGITS)));
		accounts.add(new IBANRecord("Luxembourg", 20, "LU", "LUkk bbbc cccc cccc cccc", of(3, DIGITS),
				of(13, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("North Macedonia", 19, "MK", "MK07 bbbc cccc cccc cxx", 07, of(3, DIGITS),
				of(10, ALPHA_NUMERIC), of(2, DIGITS)));
		accounts.add(new IBANRecord("Malta", 31, "MT", "MTkk bbbb ssss sccc cccc cccc cccc ccc", of(4, LETTERS),
				of(5, DIGITS), of(18, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Mauritania", 27, "MR", "MR13 bbbb bsss sscc cccc cccc cxx", 13, of(23, DIGITS)));
		accounts.add(new IBANRecord("Mauritius", 30, "MU", "MUkk bbbb bbss cccc cccc cccc 000m mm", of(4, LETTERS),
				of(19, DIGITS), of(3, LETTERS)));
		accounts.add(new IBANRecord("Monaco", 27, "MC", "MCkk bbbb bsss sscc cccc cccc cxx", of(10, DIGITS),
				of(11, ALPHA_NUMERIC), of(2, DIGITS)));
		accounts.add(new IBANRecord("Moldova", 24, "MD", "MDkk bbcc cccc cccc cccc cccc", of(2, ALPHA_NUMERIC),
				of(18, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Montenegro", 22, "ME", "ME25 bbbc cccc cccc cccc xx", 25, of(18, DIGITS)));
		accounts.add(new IBANRecord("Netherlands", 18, "NL", "NLkk bbbb cccc cccc cc", of(4, LETTERS), of(10, DIGITS)));
		accounts.add(new IBANRecord("Norway", 15, "NO", "NOkk bbbb cccc ccx", of(11, DIGITS)));
		accounts.add(new IBANRecord("Pakistan", 24, "PK", "PKkk bbbb cccc cccc cccc cccc", of(4, ALPHA_NUMERIC),
				of(16, DIGITS)));
		accounts.add(new IBANRecord("Palestinian territories", 29, "PS", "PSkk bbbb cccc cccc cccc cccc cccc c",
				of(4, ALPHA_NUMERIC), of(21, DIGITS)));
		accounts.add(new IBANRecord("Poland", 28, "PL", "PLkk bbbs sssx cccc cccc cccc cccc", of(24, DIGITS)));
		accounts.add(new IBANRecord("Portugal", 25, "PT", "PT50 bbbb ssss cccc cccc cccx x", 50, of(21, DIGITS)));
		accounts.add(new IBANRecord("Qatar", 29, "QA", "QAkk bbbb cccc cccc cccc cccc cccc c", of(4, LETTERS),
				of(21, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Romania", 24, "RO", "ROkk bbbb cccc cccc cccc cccc", of(4, LETTERS),
				of(16, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Saint Lucia", 32, "LC", "LCkk bbbb cccc cccc cccc cccc cccc cccc", of(4, LETTERS),
				of(24, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("San Marino", 27, "SM", "SMkk xbbb bbss sssc cccc cccc ccc", of(1, LETTERS),
				of(10, DIGITS), of(12, ALPHA_NUMERIC)));
		accounts.add(
				new IBANRecord("Sao Tome and Principe", 25, "ST", "STkk bbbb ssss cccc cccc cccc c", of(21, DIGITS)));
		accounts.add(new IBANRecord("Saudi Arabia", 24, "SA", "SAkk bbcc cccc cccc cccc cccc", of(2, DIGITS),
				of(18, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Serbia", 22, "RS", "RS35 bbbc cccc cccc cccc xx", 35, of(18, DIGITS)));
		accounts.add(new IBANRecord("Seychelles", 31, "SC", "SCkk bbbb bbss nnnn nnnn nnnn nnnn mmm", of(4, LETTERS),
				of(20, DIGITS), of(3, LETTERS)));
		accounts.add(new IBANRecord("Slovakia", 24, "SK", "SKkk bbbb ssss sscc cccc cccc", of(20, DIGITS)));
		accounts.add(new IBANRecord("Slovenia", 19, "SI", "SI56 bbss sccc cccc cxx", 56, of(15, DIGITS)));
		accounts.add(new IBANRecord("Spain", 24, "ES", "ESkk bbbb ssss xxcc cccc cccc", of(20, DIGITS)));
		accounts.add(new IBANRecord("Sweden", 24, "SE", "SEkk bbbc cccc cccc cccc cccc", of(20, DIGITS)));
		accounts.add(new IBANRecord("Switzerland", 21, "CH", "CHkk bbbb bccc cccc cccc c", of(5, DIGITS),
				of(12, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Tunisia", 24, "TN", "TN59 bbss sccc cccc cccc ccxx", 59, of(20, DIGITS)));
		accounts.add(new IBANRecord("Turkey", 26, "TR", "TRkk bbbb bccc cccc cccc cccc cc", of(5, DIGITS),
				of(17, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("Ukraine", 29, "UA", "UAkk bbbb bbcc cccc cccc cccc cccc c", of(6, DIGITS),
				of(19, ALPHA_NUMERIC)));
		accounts.add(new IBANRecord("United Arab Emirates", 23, "AE", "AEkk bbbc cccc cccc cccc ccc", of(3, DIGITS),
				of(16, DIGITS)));
		accounts.add(new IBANRecord("United Kingdom", 22, "GB", "GBkk bbbb ssss sscc cccc cc", of(4, LETTERS),
				of(14, DIGITS)));
		accounts.add(new IBANRecord("British Virgin Islands", 24, "VG", "VGkk bbbb cccc cccc cccc cccc",
				of(4, ALPHA_NUMERIC), of(16, DIGITS)));
		
		ibanMap = accounts.stream().collect(Collectors.toMap(IBANRecord::getPrefix, r -> r, (o1, o2) -> o1));

	}
	
	public static final BigInteger divisor = new BigInteger("97");
	
	/**
	 * Generates a random IBAN account
	 * @param countryCode
	 * @return
	 */
	public static IBANRecord generate(String countryCode) {
		if (!ibanMap.containsKey(countryCode)) {
			throw new IllegalArgumentException("Cannot find mapping for country code " + countryCode);
		}
		IBANRecord rec = ibanMap.get(countryCode).clone();
		for (CharsGroup group : rec.getGroups()) {
			CommonGenerator.fill(group.getChars(), group.getType());
		}
		if (!rec.hasDefaultChecksum()) {
			int checkSum = calculateCheckSum(rec.getUnformattedNumber(), rec.getLength());
			rec.setCheckSum(checkSum);
		}
		return rec;

	}


	/**
	 * 
	 * Check that the total IBAN length is correct as per the country. If not, the
	 * IBAN is invalid
	 * 
	 * Move the four initial characters to the end of the string
	 * 
	 * Replace each letter in the string with two digits, thereby expanding the
	 * string, where A = 10, B = 11, ..., Z = 35 Interpret the string as a decimal
	 * integer and compute the remainder of that number on division by 97
	 * 
	 * If the remainder is 1, the check digit test is passed and the IBAN might be
	 * valid.
	 *
	 * Example (fictitious United Kingdom bank, sort code 12-34-56, account number
	 * 98765432):
	 *
	 * IBAN:GB82 WEST 1234 5698 7654 32
	 * 
	 * Rearrange:W E S T12345698765432 G B82
	 * 
	 * Convert to integer:3214282912345698765432161182
	 * 
	 * Compute remainder:3214282912345698765432161182 mod 97 = 1
	 * 
	 * @param iban
	 * @return
	 */
	public static boolean valid(String iban) {
		if (iban == null) {
			throw new IllegalArgumentException("account number cannot be null");
		}
		if (iban.length() < 4) {
			throw new IllegalArgumentException("Incorrect length of account number, should be > 4");
		}
		StringBuilder sb = new StringBuilder();
		char[] chars = iban.toCharArray();
		for (int i = 4; i < chars.length; i++) {
			if (isDigit(chars[i])) {
				sb.append(chars[i]);
			}else {
				sb.append(chars[i] - 'A' + 10);
			}
		}
		sb.append(chars[0] - 'A' + 10);
		sb.append(chars[1] - 'A' + 10);
		sb.append(chars[2]);
		sb.append(chars[3]);
		BigInteger bi = new BigInteger(sb.toString());
		int res = bi.remainder(divisor).intValue();
		return res == 1;

	}
	
	

	/**
	 * 
	 * Check that the total IBAN length is correct as per the country. If not, the
	 * IBAN is invalid.
	 * 
	 * Replace the two check digits by 00 (e.g., GB00 for the UK). Move the four
	 * initial characters to the end of the string.
	 * 
	 * Replace the letters in the string with digits, expanding the string as
	 * necessary, such that A or a = 10, B or b = 11, and Z or z = 35. Each
	 * alphabetic character is therefore replaced by 2 digits
	 * 
	 * Convert the string to an integer (i.e. ignore leading zeroes).
	 * 
	 * Calculate mod-97 of the new number, which results in the remainder.
	 * 
	 * Subtract the remainder from 98, and use the result for the two check digits.
	 * If the result is a single digit number, pad it with a leading 0 to make a
	 * two-digit number.
	 * 
	 * @param iban
	 * @return checksum
	 */
	public static int calculateCheckSum(String iban, int len) {
		if (iban == null) {
			throw new IllegalArgumentException("account number cannot be null");
		}
		if (iban.length() != len) {
			throw new IllegalArgumentException("Incorrect length of account number: " + iban.length());
		}
		StringBuilder sb = new StringBuilder();
		char[] chars = iban.toCharArray();
		for (int i = 4; i < chars.length; i++) {
			if (isDigit(chars[i])) {
				sb.append(chars[i]);
			}else {
				sb.append(chars[i] - 'A' + 10);
			}
		}
		sb.append(chars[0] - 'A' + 10);
		sb.append(chars[1] - 'A' + 10);
		sb.append(chars[2]);
		sb.append(chars[3]);
		BigInteger bi = new BigInteger(sb.toString());
		int res = 98 - bi.remainder(divisor).intValue();
		return res;

	}
	
	public static Set<String> getAvailableCountries(){
		return ibanMap.keySet();
	}
	
	private static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

}
