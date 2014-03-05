package com.umeng

/**
 * Created with IntelliJ IDEA.
 * User: wangtianju
 * Date: 3/3/14
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
object AdnRequestFilter {

  def removeIndex(ix: Int, internalIdList: Seq[String]) = {
    if (internalIdList.size < ix) internalIdList
    else internalIdList.take(ix) ++ internalIdList.drop(ix+1)
  }

  def convertRequest(request: String): String = {
    val split_req = request.split(" ")
    val uri = split_req(6)
    val split_uri = uri.split("\\?")
    val params = split_uri(1) + "&category=2&sup_id=ssp_umeng_adn"

    val new_params = params.split("&").toList map {pair =>
      val kv = pair.split("=")
      val new_key = AdnLogConstant.key_map.getOrElse(kv(0), kv(0))
      val value = if(kv.length > 1) kv(1) else ""
      new_key -> value
    } map { case (k,v) =>
      k + "=" + v
    } mkString("&")

    val new_req = split_req.updated(6,"/api/q?" +  new_params)
    val cookie_removed = removeIndex(new_req.length-2, new_req)
    cookie_removed.mkString(" ")
  }

  def main(args: Array[String]){
    val req = "211.103.189.227 - - [03/Mar/2014:14:24:40 +0800] \"GET /a/l?" +
      "clientid=c92a10acece6f6b0&slotid=7a1c25dd237cd5c5&request_type=0" +
      "&slotidx=0&app_key=&opid=9633a869661daa922a6cf83267f2b78fb2820592&" +
      "macid=38:48:4C:F1:B0:7C&app_version=1.30&sdk_version=1.3.2%286d98569%29&" +
      "os=iOS&os_version=6.1.3&resolution=1024x768&access=&country=US&lat=&" +
      "lng=&timezone=8&language=zh-Hans&device_model=iPad2%2C5&carrier=(null)&" +
      "is_jailbroken=NO&package_name=com.spring.yinhe&" +
      "display_name=%E4%BA%8C%E6%9C%88%E6%B2%B3%E4%BD%9C%E5%93%81-%E5%B8%9D%E7%8E%8B%E5%85%A8%E9%9B%86&" +
      "is_pirated=YES HTTP/1.1\" 200 167 \"-\" \"epubReader/1.30 CFNetwork/609.1.4 Darwin/13.0.0\" - 0.001"
      println(req)
      println(convertRequest(req))
  }
}

object AdnLogConstant {

  //log param key map
  val key_map = Map(
    "clientid" -> "media_id",
    "slotid" -> "slot_id",
    "adholder_id" -> "slot_id",
    "slotidx" -> "slot_idx",
    "adholder_idx" -> "slot_idx",
    "styleid" -> "layout_type",
    "style_id" -> "layout_type",
    "ad_id" -> "promoter",
    "creative_id" -> "promoter",
    "idmd5" -> "idmd5",
    "imei" -> "device_id",
    "macid" -> "mc",
    "opid" -> "oid",
    "idfs" -> "aid",
    "is_jailbroken" -> "is_jb",
    "packagename" -> "app_package_name",
    "package_name" -> "app_package_name",
    "app_version" -> "app_version_name",
    "version_code" -> "app_version_code",
    "app_key" -> "xp_app_key", /* app_key in adn, avoid conflict*/
    "appkey" -> "xp_app_key",
    "click_url" -> "url" /* for /r/t, to trigger redirect */
  )
"211.103.189.227 - - [03/Mar/2014:14:24:40 +0800] \"GET /api/q?media_id=c92a10acece6f6b0&slot_id=7a1c25dd237cd5c5&request_type=0&slot_idx=0&xp_app_key=&oid=9633a869661daa922a6cf83267f2b78fb2820592&mc=38:48:4C:F1:B0:7C&app_version_name=1.30&sdk_version=1.3.2%286d98569%29&os=iOS&os_version=6.1.3&resolution=1024x768&access=&country=US&lat=&lng=&timezone=8&language=zh-Hans&device_model=iPad2%2C5&carrier=(null)&is_jb=NO&app_package_name=com.spring.yinhe&display_name=%E4%BA%8C%E6%9C%88%E6%B2%B3%E4%BD%9C%E5%93%81-%E5%B8%9D%E7%8E%8B%E5%85%A8%E9%9B%86&is_pirated=YES&category=2&sup_id=ssp_umeng_adn HTTP/1.1\" 200 167 \"-\" \"epubReader/1.30 CFNetwork/609.1.4 Darwin/13.0.0\" 0.001"
}
