package br.com.alura.orgs.extensions

import java.security.MessageDigest

fun String.toHash(algoritomo:String = "SHA-256"):String{
    return MessageDigest
        .getInstance(algoritomo)
        .digest(this.toByteArray())
        .fold("",{str,byte-> str+ "%02x".format(byte)})
}