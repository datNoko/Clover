/*
 * Clover - 4chan browser https://github.com/Floens/Clover/
 * Copyright (C) 2014  Floens
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.floens.chan.chan;

import java.util.Locale;

public class ChanUrls {
    public static String getCatalogUrl(String board) {
        return "https://a.4cdn.org/" + board + "/catalog.json";
    }

    public static String getPageUrl(String board, int pageNumber) {
        return "https://a.4cdn.org/" + board + "/" + (pageNumber + 1) + ".json";
    }

    public static String getThreadUrl(String board, int no) {
        return "https://a.4cdn.org/" + board + "/thread/" + no + ".json";
    }

    public static String getCaptchaChallengeUrl() {
        return "https://www.google.com/recaptcha/api/challenge?k=6Ldp2bsSAAAAAAJ5uyx_lx34lJeEpTLVkP5k04qc";
    }

    public static String getCaptchaImageUrl(String challenge) {
        return "https://www.google.com/recaptcha/api/image?c=" + challenge;
    }

    public static String getImageUrl(String board, String code, String extension) {
        return "https://i.4cdn.org/" + board + "/" + code + "." + extension;
    }

    public static String getThumbnailUrl(String board, String code) {
        return "https://t.4cdn.org/" + board + "/" + code + "s.jpg";
    }

    public static String getCountryFlagUrl(String countryCode) {
        return "https://s.4cdn.org/image/country/" + countryCode.toLowerCase(Locale.ENGLISH) + ".gif";
    }

    public static String getBoardsUrl() {
        return "https://a.4cdn.org/boards.json";
    }

    public static String getReplyUrl(String board) {
        return "https://sys.4chan.org/" + board + "/post";
    }

    public static String getDeleteUrl(String board) {
        return "https://sys.4chan.org/" + board + "/imgboard.php";
    }

    public static String getBoardUrlDesktop(String board) {
        return "https://boards.4chan.org/" + board + "/";
    }

    public static String getThreadUrlDesktop(String board, int no) {
        return "https://boards.4chan.org/" + board + "/thread/" + no;
    }

    public static String getCatalogUrlDesktop(String board) {
        return "https://boards.4chan.org/" + board + "/catalog";
    }

    public static String getPassUrl() {
        return "https://sys.4chan.org/auth";
    }
}
