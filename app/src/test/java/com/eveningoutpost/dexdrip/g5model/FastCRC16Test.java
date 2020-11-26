package com.eveningoutpost.dexdrip.g5model;

import com.eveningoutpost.dexdrip.HexTestTools;

import org.junit.Test;

import static com.eveningoutpost.dexdrip.g5model.FastCRC16.calculate;
import static com.google.common.truth.Truth.assertWithMessage;

// jamorham

public class FastCRC16Test extends HexTestTools {

    private static final int[] TEST_DOMAIN = {0x0000, 0x2110, 0x4220, 0x6330, 0x8440, 0xA550, 0xC660, 0xE770, 0x0881, 0x2991, 0x4AA1, 0x6BB1, 0x8CC1, 0xADD1, 0xCEE1, 0xEFF1, 0x3112, 0x1002, 0x7332, 0x5222, 0xB552, 0x9442, 0xF772, 0xD662, 0x3993, 0x1883, 0x7BB3, 0x5AA3, 0xBDD3, 0x9CC3, 0xFFF3, 0xDEE3, 0x6224, 0x4334, 0x2004, 0x0114, 0xE664, 0xC774, 0xA444, 0x8554, 0x6AA5, 0x4BB5, 0x2885, 0x0995, 0xEEE5, 0xCFF5, 0xACC5, 0x8DD5, 0x5336, 0x7226, 0x1116, 0x3006, 0xD776, 0xF666, 0x9556, 0xB446, 0x5BB7, 0x7AA7, 0x1997, 0x3887, 0xDFF7, 0xFEE7, 0x9DD7, 0xBCC7, 0xC448, 0xE558, 0x8668, 0xA778, 0x4008, 0x6118, 0x0228, 0x2338, 0xCCC9, 0xEDD9, 0x8EE9, 0xAFF9, 0x4889, 0x6999, 0x0AA9, 0x2BB9, 0xF55A, 0xD44A, 0xB77A, 0x966A, 0x711A, 0x500A, 0x333A, 0x122A, 0xFDDB, 0xDCCB, 0xBFFB, 0x9EEB, 0x799B, 0x588B, 0x3BBB, 0x1AAB, 0xA66C, 0x877C, 0xE44C, 0xC55C, 0x222C, 0x033C, 0x600C, 0x411C, 0xAEED, 0x8FFD, 0xECCD, 0xCDDD, 0x2AAD, 0x0BBD, 0x688D, 0x499D, 0x977E, 0xB66E, 0xD55E, 0xF44E, 0x133E, 0x322E, 0x511E, 0x700E, 0x9FFF, 0xBEEF, 0xDDDF, 0xFCCF, 0x1BBF, 0x3AAF, 0x599F, 0x788F, 0x8891, 0xA981, 0xCAB1, 0xEBA1, 0x0CD1, 0x2DC1, 0x4EF1, 0x6FE1, 0x8010, 0xA100, 0xC230, 0xE320, 0x0450, 0x2540, 0x4670, 0x6760, 0xB983, 0x9893, 0xFBA3, 0xDAB3, 0x3DC3, 0x1CD3, 0x7FE3, 0x5EF3, 0xB102, 0x9012, 0xF322, 0xD232, 0x3542, 0x1452, 0x7762, 0x5672, 0xEAB5, 0xCBA5, 0xA895, 0x8985, 0x6EF5, 0x4FE5, 0x2CD5, 0x0DC5, 0xE234, 0xC324, 0xA014, 0x8104, 0x6674, 0x4764, 0x2454, 0x0544, 0xDBA7, 0xFAB7, 0x9987, 0xB897, 0x5FE7, 0x7EF7, 0x1DC7, 0x3CD7, 0xD326, 0xF236, 0x9106, 0xB016, 0x5766, 0x7676, 0x1546, 0x3456, 0x4CD9, 0x6DC9, 0x0EF9, 0x2FE9, 0xC899, 0xE989, 0x8AB9, 0xABA9, 0x4458, 0x6548, 0x0678, 0x2768, 0xC018, 0xE108, 0x8238, 0xA328, 0x7DCB, 0x5CDB, 0x3FEB, 0x1EFB, 0xF98B, 0xD89B, 0xBBAB, 0x9ABB, 0x754A, 0x545A, 0x376A, 0x167A, 0xF10A, 0xD01A, 0xB32A, 0x923A, 0x2EFD, 0x0FED, 0x6CDD, 0x4DCD, 0xAABD, 0x8BAD, 0xE89D, 0xC98D, 0x267C, 0x076C, 0x645C, 0x454C, 0xA23C, 0x832C, 0xE01C, 0xC10C, 0x1FEF, 0x3EFF, 0x5DCF, 0x7CDF, 0x9BAF, 0xBABF, 0xD98F, 0xF89F, 0x176E, 0x367E, 0x554E, 0x745E, 0x932E, 0xB23E, 0xD10E, 0xF01E, 0x79BD};
    private static final byte[] TEST_DOMAIN_2 = hexStringToByteArray("f78c276cc73372619e641948ce17ca0375e07b27e8a89a970487d40392db947e24bea031c4e4da1e0779c2755a5f20b6b49ed523b68a0414665bd41c341cd33e99ab4d9fd474b9e47650557f048ce5f0ffbfbac92450d8c7e3a0c3fa5260ef930b487bf6979dc52a8efcf7f441bc5773b03c86d0ec7cf8613f129834c53cc5dee9d2429bc610a8a85fda15881198f0a1517c07729a347a2a3717628010328ba3b5126652cbbe6dfb306522efe8a8035c3082ce13115a02c969f3b346a4f1b35c182b44e422a042808715da0002f9784500f5bca29dea848df6d36589a24da9915edaf76d927493be1bf2f18f877717848a9bda85f196a95f09088faa2592b1684b5c1335b10234316156cc7acb7b13e682b27d36748fece6b247467c96e697b9bcbf924c1114d3671c700c6fb2a2f8f29db4ebd91cda349366fd0ac9fb91496c");
    private static final int[] TEST_DOMAIN_3 = {0x0000, 0xF89F, 0x52DA, 0xB26C, 0x00B2, 0x322E, 0x9CF1, 0xEB3D, 0x7970, 0xE065, 0x21F0, 0x074D, 0xA557, 0x90B7, 0xEA25, 0xC1E6, 0x8B6C, 0x1808, 0x2664, 0xDEC5, 0x6C03, 0x4520, 0x8055, 0xA3A8, 0xBC64, 0xA6D0, 0x128C, 0xFDC9, 0x0685, 0xD664, 0x3480, 0xB566, 0x3926, 0x4219, 0x0D87, 0x8559, 0xAE68, 0x66DA, 0x9DB1, 0xCD40, 0x3B76, 0xB655, 0xEE53, 0x987D, 0x0819, 0xA770, 0x8D72, 0xB7F7, 0xE5EF, 0x9E0E, 0xB91D, 0x44E1, 0x0EBD, 0x6BBF, 0xF60D, 0x2967, 0xF467, 0x21E4, 0x3477, 0x89B1, 0x47ED, 0x541D, 0x2144, 0x5ED2, 0xA262, 0x74FC, 0x125E, 0x5230, 0x0516, 0x0EFC, 0x801E, 0x0D45, 0xCBA8, 0xB3E1, 0xFA04, 0xD4B0, 0xA3FC, 0x17CD, 0xE54F, 0xA0F1, 0x21B0, 0x2B98, 0x857F, 0xE90C, 0xE960, 0x40E1, 0xFAF7, 0x8D2F, 0x26F1, 0x7314, 0x5F94, 0x1275, 0x6772, 0x6243, 0x0176, 0x9013, 0x8801, 0x4A29, 0x8736, 0x691E, 0x2615, 0xCA97, 0x4A6B, 0x241E, 0xD752, 0xF1DD, 0x43C5, 0x1155, 0xCBB4, 0xBA74, 0x44E2, 0x7EB3, 0x4CA7, 0xD62E, 0x73E4, 0xE43F, 0xC1E8, 0x8481, 0xB2BA, 0x86DA, 0xB090, 0x05F4, 0xE898, 0x00E8, 0xF10A, 0xA3D9, 0x8B0E, 0x27E3, 0xFEC0, 0x4B4B, 0x9059, 0x5A33, 0xE26E, 0xE2D6, 0x8A5B, 0x7C55, 0xB242, 0x9C71, 0x8198, 0x25C1, 0xEDFC, 0x83C1, 0xDC48, 0xD3FA, 0x9E38, 0x8F63, 0xDE6C, 0xCD03, 0xB6A3, 0x7A11, 0xC70E, 0x13F9, 0x1EE8, 0x9224, 0x3094, 0xD9BF, 0xBC1E, 0xCE5D, 0x4953, 0x7503, 0xEAC0, 0x32C4, 0xBB99, 0xF0A5, 0xF86F, 0x6E0D, 0xB8F9, 0x3D7B, 0x88AC, 0x35CA, 0x0571, 0x966F, 0x8807, 0xC184, 0xEE24, 0x85BA, 0xE818, 0x6A4D, 0xA342, 0x04F3, 0x2EF9, 0x2652, 0x08A7, 0x4FED, 0xE62B, 0x868E, 0x3A29, 0xF318, 0x3B48, 0xA207, 0xD92D, 0x77BB, 0xC9FA, 0x6C14, 0xBCAB, 0xC1B0, 0x71DB, 0x360F, 0x0572, 0x5333, 0xB8C4, 0xA7C0, 0xD83C, 0xE845, 0x61F0, 0x5DAE, 0x1277, 0xEFE3, 0x2C3A, 0x19BB, 0x0AB0, 0x8CCB, 0x8F71, 0xA2B3, 0xDC69, 0x83F0, 0x3A2C, 0x3750, 0xEB96, 0x7C34, 0x760A, 0xE242, 0xEF13, 0x91E9, 0x9F6E, 0x53A9, 0xF41D, 0x64A8, 0xE9ED, 0x7866, 0x734A, 0x5429, 0x5EA7, 0x5738, 0x062F, 0xB32C, 0x8936, 0xFA3E, 0xED23, 0xD79B, 0xDE34, 0x1598, 0x6325, 0xF07D, 0x179E, 0x498A, 0xBD9A, 0x30BB, 0xAA8D, 0x0C7B, 0xF442, 0xE1FC, 0x33DB, 0xD13D, 0x0595, 0xE661, 0x2977, 0xDFDE, 0xCA6E, 0x3A65, 0xF560, 0x5C2E, 0xEEB9, 0x25AE, 0x5657, 0x9500, 0x33AF, 0xC56F, 0x9487, 0x481D, 0x6044, 0x124A, 0x6666, 0xAADB, 0x8F57, 0x280A, 0xDFDF, 0x81DB, 0x719B, 0x707F, 0x9062, 0x7D5B, 0xBDAE, 0x26C1, 0x7650, 0x8ACF, 0x4B3F, 0xE27F, 0x8A5B, 0xC907, 0xD3EF, 0x3A7C, 0x530C, 0x9C90, 0x0C4D, 0x776E, 0x29E6, 0x5567, 0xD637, 0x3851, 0x9DEF, 0x5816, 0x5FBF, 0x2367, 0x1CF0, 0x0BA1, 0x9449, 0xA801, 0x75E2, 0xD17B, 0xCB74, 0xC483, 0x31D6, 0xDB96, 0xCD06, 0x8C0C, 0xE905, 0xD1E7, 0x51CF, 0x4EA0, 0xC056};

    @Test
    public void calculateTest() {
        for (int i = 0; i < 256; i++) {
            assertWithMessage("domain test: " + i).that(calculate((byte) i)).isEqualTo(toTwoBytes(TEST_DOMAIN[i]));
        }

        assertWithMessage("test domain 2 full").that(calculate(TEST_DOMAIN_2)).isEqualTo(toTwoBytes(0xC056));
        assertWithMessage("test domain 2 full indexed").that(calculate(TEST_DOMAIN_2, TEST_DOMAIN_2.length)).isEqualTo(toTwoBytes(0xC056));

        for (int i = 0; i <= TEST_DOMAIN_2.length; i++) {
            assertWithMessage("reference test: " + i).that(calculate(TEST_DOMAIN_2, i)).isEqualTo(toTwoBytes(TEST_DOMAIN_3[i]));
        }
    }
}