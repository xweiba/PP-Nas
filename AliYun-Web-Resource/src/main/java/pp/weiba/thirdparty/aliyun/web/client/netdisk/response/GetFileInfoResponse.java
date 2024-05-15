package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 不带预览图
 * {
 * "drive_id": "111",
 * "domain_id": "bj29",
 * "file_id": "1111",
 * "name": "1111",
 * "type": "folder",
 * "created_at": "2022-12-19T04:06:56.839Z",
 * "updated_at": "2022-12-19T04:06:56.839Z",
 * "hidden": false,
 * "starred": false,
 * "status": "available",
 * "user_meta": "{\"channel\":\"file_upload\",\"client\":\"windows\"}",
 * "parent_file_id": "111",
 * "encrypt_mode": "none",
 * "meta_name_punish_flag": 0,
 * "meta_name_investigation_status": 0,
 * "creator_type": "User",
 * "creator_id": "111",
 * "last_modifier_type": "User",
 * "last_modifier_id": "111",
 * "user_tags": {
 * "channel": "file_upload",
 * "client": "windows",
 * "device_id": "111",
 * "device_name": "Unknown"
 * },
 * "ex_fields_info": {
 * "image_count": 0
 * },
 * "sync_flag": false,
 * "sync_device_flag": false,
 * "sync_meta": "",
 * "trashed": false,
 * "download_url": ""
 * }
 * <p>
 * 带预览图视频
 * {
 * "drive_id": "18654654",
 * "domain_id": "bj29",
 * "file_id": "65f8980ad90d7bcea264475ea8abce0aaa9ae012",
 * "name": "IMG_2506.MOV",
 * "type": "file",
 * "content_type": "application/oct-stream",
 * "created_at": "2024-03-18T19:37:46.805Z",
 * "updated_at": "2024-03-18T19:37:46.805Z",
 * "file_extension": "mov",
 * "mime_type": "video/quicktime",
 * "mime_extension": "mov",
 * "hidden": false,
 * "size": 2811735,
 * "starred": false,
 * "status": "available",
 * "user_meta": "{\"channel\":\"file_upload\",\"client\":\"windows\"}",
 * "upload_id": "rapid-112f6a70-2e78-49eb-adaa-929546381740",
 * "parent_file_id": "65f88c65d21a7dd760f04d5f8336b8058d4ab6f5",
 * "crc64_hash": "15273709128597645316",
 * "content_hash": "4D4ADDC2C88F156B7AE9C3CB92ADA50A04796CB5",
 * "content_hash_name": "sha1",
 * "download_url": "",
 * "url": "https://cn-beijing-data.aliyundrive.net/gDghRXuN%2F18654654%2F65f88c6150cda47812bd4ca4b8744eb70d09451b%2F65f88c61a73950d5a80f47698d01b01fa3a992ad?callback=eyJjYWxsYmFja1VybCI6Imh0dHA6Ly9iajI5LmFwaS1ocC5hbGl5dW5wZHMuY29tL3YyL2ZpbGUvZG93bmxvYWRfY2FsbGJhY2siLCJjYWxsYmFja0JvZHkiOiJodHRwSGVhZGVyLnJhbmdlPSR7aHR0cEhlYWRlci5yYW5nZX1cdTAwMjZidWNrZXQ9JHtidWNrZXR9XHUwMDI2b2JqZWN0PSR7b2JqZWN0fVx1MDAyNmRvbWFpbl9pZD0ke3g6ZG9tYWluX2lkfVx1MDAyNnVzZXJfaWQ9JHt4OnVzZXJfaWR9XHUwMDI2ZHJpdmVfaWQ9JHt4OmRyaXZlX2lkfVx1MDAyNmZpbGVfaWQ9JHt4OmZpbGVfaWR9XHUwMDI2cGRzX3BhcmFtcz0ke3g6cGRzX3BhcmFtc31cdTAwMjZ2ZXJzaW9uPSR7eDp2ZXJzaW9ufSIsImNhbGxiYWNrQm9keVR5cGUiOiJhcHBsaWNhdGlvbi94LXd3dy1mb3JtLXVybGVuY29kZWQiLCJjYWxsYmFja1N0YWdlIjoiYmVmb3JlLWV4ZWN1dGUiLCJjYWxsYmFja0ZhaWx1cmVBY3Rpb24iOiJpZ25vcmUifQ%3D%3D&callback-var=eyJ4OmRvbWFpbl9pZCI6ImJqMjkiLCJ4OnVzZXJfaWQiOiIwMDc1ODlkNzczMzk0ZGQxODdjMzk1ZWUzYzc3NDdiMCIsIng6ZHJpdmVfaWQiOiIxMzI5ODY1MCIsIng6ZmlsZV9pZCI6IjY1Zjg5ODBhZDkwZDdiY2VhMjY0NDc1ZWE4YWJjZTBhYWE5YWUwMTIiLCJ4OnBkc19wYXJhbXMiOiJ7XCJhcFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwifSIsIng6dmVyc2lvbiI6InYzIn0%3D&di=bj29&dr=18654654&f=65f8980ad90d7bcea264475ea8abce0aaa9ae012&pds-params=%7B%22ap%22%3A%2225dzX3vbYqktVxyX%22%7D&response-content-disposition=attachment%3B%20filename%2A%3DUTF-8%27%27&security-token=CAISvgJ1q6Ft5B2yfSjIr5b2KNDO2opJ%2FPOlcxHrnlQDZ%2BUZhIaY0zz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNSiBEVfMqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYne0yJq1B5bCbP7AhWvDNQ3S7jN6YihvSt3zmA4YsrdqJPW1dKDogPIx4aBwHbHMFKlwddMkwuiM3K2Z%2FXtuMkagAE%2BSoF5x5Z7GN65GySrWKUVuFeP9SpNK1m5wKsz40t1GZK3n0XBmClsQvdxMKzG%2Fy%2B%2FMfs6dyFIL%2FAR3P1jFvS8civ0ZGuOAabcC0zhJ9xqQDyznxGu0uDlX%2F6WyoZGDh3nN6pXyeBnaa2DpASd%2BuYxSmKHc4%2B6HTe3UXM9ilih3iAA&u=0075833233234dd187c395ee3c7747b0&x-oss-access-key-id=STS.NUCcjz7UhK1Nq7ZzTVkj5kC31&x-oss-additional-headers=referer&x-oss-expires=1714461995&x-oss-signature=jJYgua1VIVxEtIZp57eDuXQWE%2FQXUvNw16I2xhh%2FSVg%3D&x-oss-signature-version=OSS2",
 * "thumbnail": "https://cn-beijing-data.aliyundrive.net/gDghRXuN%2F18654654%2F65f88c6150cda47812bd4ca4b8744eb70d09451b%2F65f88c61a73950d5a80f47698d01b01fa3a992ad?security-token=CAISvgJ1q6Ft5B2yfSjIr5b2KNDO2opJ%2FPOlcxHrnlQDZ%2BUZhIaY0zz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNSiBEVfMqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYne0yJq1B5bCbP7AhWvDNQ3S7jN6YihvSt3zmA4YsrdqJPW1dKDogPIx4aBwHbHMFKlwddMkwuiM3K2Z%2FXtuMkagAE%2BSoF5x5Z7GN65GySrWKUVuFeP9SpNK1m5wKsz40t1GZK3n0XBmClsQvdxMKzG%2Fy%2B%2FMfs6dyFIL%2FAR3P1jFvS8civ0ZGuOAabcC0zhJ9xqQDyznxGu0uDlX%2F6WyoZGDh3nN6pXyeBnaa2DpASd%2BuYxSmKHc4%2B6HTe3UXM9ilih3iAA&x-oss-access-key-id=STS.NUCcjz7UhK1Nq7ZzTVkj5kC31&x-oss-additional-headers=referer&x-oss-expires=1714461995&x-oss-process=video%2Fsnapshot%2Ct_120000%2Cf_jpg%2Cm_lfit%2Cw_256%2Car_auto%2Cm_fast&x-oss-signature=0vpdi7AMd98bm6BbMYdkZ%2BXPZbV2PQzbFrcUKLiXYJs%3D&x-oss-signature-version=OSS2",
 * "category": "video",
 * "encrypt_mode": "none",
 * "video_media_metadata": {
 * "time": "2023-04-08T07:03:31.000Z",
 * "width": 1920,
 * "height": 1440,
 * "location": "",
 * "country": "中国",
 * "province": "北京",
 * "city": "北京市",
 * "district": "",
 * "township": "",
 * "address_line": "北京市",
 * "video_media_video_stream": [
 * {
 * "duration": "1.733333",
 * "clarity": "1440",
 * "fps": "11400/533",
 * "bitrate": "11911001",
 * "code_name": "hevc",
 * "frame_count": "38"
 * }
 * ],
 * "video_media_audio_stream": [
 * {
 * "duration": "1.731678",
 * "channels": 1,
 * "channel_layout": "",
 * "bit_rate": "705600",
 * "code_name": "pcm_s16le",
 * "sample_rate": "44100"
 * }
 * ],
 * "duration": "1.733333"
 * },
 * "punish_flag": 0,
 * "meta_name_punish_flag": 0,
 * "meta_name_investigation_status": 0,
 * "creator_type": "User",
 * "creator_id": "0075833233234dd187c395ee3c7747b0",
 * "last_modifier_type": "User",
 * "last_modifier_id": "0075833233234dd187c395ee3c7747b0",
 * "user_tags": {
 * "channel": "file_upload",
 * "client": "windows",
 * "device_id": "36fb6f9df2c4dfb53bff0d0f1699d3eebaafe02b67554d7f493298e03d8f1437",
 * "device_name": "LAPTOP-MCCB4FFK",
 * "version": "v4.13.1"
 * },
 * "revision_id": "65f8980adba925c63a5242c288fd2315148164bd",
 * "revision_version": 1,
 * "sync_flag": false,
 * "sync_device_flag": false,
 * "sync_meta": "",
 * "trashed": false
 * }
 * <p>
 * 图片
 * {
 * "drive_id": "18654654",
 * "domain_id": "bj29",
 * "file_id": "65f898067fc32b4d938d481fbbc618972d63d3d7",
 * "name": "IMG_6176.HEIC",
 * "type": "file",
 * "content_type": "application/oct-stream",
 * "created_at": "2024-03-18T19:37:42.118Z",
 * "updated_at": "2024-03-18T19:37:42.118Z",
 * "file_extension": "heic",
 * "mime_type": "image/heic",
 * "mime_extension": "heic",
 * "hidden": false,
 * "size": 2493898,
 * "starred": false,
 * "status": "available",
 * "user_meta": "{\"channel\":\"file_upload\",\"client\":\"windows\"}",
 * "labels": [
 * "植物",
 * "其他事物",
 * "旅游&地理",
 * "体育运动",
 * "面部",
 * "衣服",
 * "T恤",
 * "女孩",
 * "徒步",
 * "男性",
 * "女性",
 * "颜色",
 * "服装",
 * "树",
 * "自然景观",
 * "人",
 * "丛林",
 * "森林",
 * "绿色",
 * "树林_森林"
 * ],
 * "upload_id": "rapid-af153238-8108-402f-82e5-49b8bf6c11b1",
 * "parent_file_id": "65f88c65d21a7dd760f04d5f8336b8058d4ab6f5",
 * "crc64_hash": "6136284431558099613",
 * "content_hash": "39FA7F1B366D3A98E145CC6BC02383DBC645FFFB",
 * "content_hash_name": "sha1",
 * "download_url": "",
 * "url": "https://cn-beijing-data.aliyundrive.net/3OTZIorN%2F18654654%2F65f88c5590379015e9564ba6852a9bd555dea2b9%2F65f88c555baafd0eba1a47c789cd30a931c12d60?security-token=CAISvgJ1q6Ft5B2yfSjIr5b0Gu%2FZv5lP1vOvM0PgnEc%2FfeFCl4b40Dz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXTee1fMqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYneg%2FLe1lOHLuHwufJ7FxfIREfquk63pvSlHLcLPe0Kjzzleo2k1XRPVFF%2B535IaHXuToXDnvSiMFBT%2FfXtuMkagAGCs8RA3Qu5KIXgtgK2XhwxPJwf7dB2VGq1o%2FnQvLV2o8SFqtiuqxXCnbtsYigEgxIBwVnLcOucU3fH1RAFoBwOr2sZzg5A9xJ5CIy97ghnYiR8rAKQbgzUNCO7ciO0H%2FpjRXW5hROGCjKqIXNGrT6TxDAvL3ByJwBJAG3kQu8TOSAA&x-oss-access-key-id=STS.NUAQUmRFna1D1eQxGjqnnxCS2&x-oss-additional-headers=referer&x-oss-expires=1714462432&x-oss-process=image%2Fresize%2Cw_1920%2Fformat%2Cavif&x-oss-signature=YUp95rZH9rKhax8lP%2BfpYHkOdMTuj48ohu5jkJtxg6A%3D&x-oss-signature-version=OSS2",
 * "thumbnail": "https://cn-beijing-data.aliyundrive.net/3OTZIorN%2F18654654%2F65f88c5590379015e9564ba6852a9bd555dea2b9%2F65f88c555baafd0eba1a47c789cd30a931c12d60?security-token=CAISvgJ1q6Ft5B2yfSjIr5b0Gu%2FZv5lP1vOvM0PgnEc%2FfeFCl4b40Dz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXTee1fMqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYneg%2FLe1lOHLuHwufJ7FxfIREfquk63pvSlHLcLPe0Kjzzleo2k1XRPVFF%2B535IaHXuToXDnvSiMFBT%2FfXtuMkagAGCs8RA3Qu5KIXgtgK2XhwxPJwf7dB2VGq1o%2FnQvLV2o8SFqtiuqxXCnbtsYigEgxIBwVnLcOucU3fH1RAFoBwOr2sZzg5A9xJ5CIy97ghnYiR8rAKQbgzUNCO7ciO0H%2FpjRXW5hROGCjKqIXNGrT6TxDAvL3ByJwBJAG3kQu8TOSAA&x-oss-access-key-id=STS.NUAQUmRFna1D1eQxGjqnnxCS2&x-oss-additional-headers=referer&x-oss-expires=1714462432&x-oss-process=image%2Fresize%2Cw_256%2Fformat%2Cavif&x-oss-signature=GNWYYDloEaqnzWzdx9VU2VYWFPhveZC6A%2F%2F5YcWKYPk%3D&x-oss-signature-version=OSS2",
 * "category": "image",
 * "encrypt_mode": "none",
 * "image_media_metadata": {
 * "time": "2023-09-15T05:03:10.000Z",
 * "width": 4032,
 * "height": 3024,
 * "location": "",
 * "country": "",
 * "province": "",
 * "city": "",
 * "district": "",
 * "township": "",
 * "address_line": "",
 * "image_tags": [
 * {
 * "name": "服装",
 * "tag_level": 2,
 * "confidence": 1,
 * "parent_name": "衣服",
 * "centric_score": 0.752
 * },
 * {
 * "name": "球鞋",
 * "tag_level": 2,
 * "confidence": 1,
 * "parent_name": "衣服",
 * "centric_score": 0.669
 * },
 * {
 * "name": "鞋",
 * "tag_level": 2,
 * "confidence": 0.949,
 * "parent_name": "衣服",
 * "centric_score": 0.668
 * },
 * {
 * "name": "森林",
 * "tag_level": 3,
 * "confidence": 0.949,
 * "parent_name": "自然景观",
 * "centric_score": 0.725
 * },
 * {
 * "name": "牛仔裤",
 * "tag_level": 2,
 * "confidence": 0.941,
 * "parent_name": "衣服",
 * "centric_score": 0.675
 * },
 * {
 * "name": "鞋类",
 * "tag_level": 2,
 * "confidence": 0.941,
 * "parent_name": "衣服",
 * "centric_score": 0.667
 * },
 * {
 * "name": "幼儿",
 * "tag_level": 2,
 * "confidence": 0.933,
 * "parent_name": "年龄段",
 * "centric_score": 0.667
 * },
 * {
 * "name": "树",
 * "tag_level": 2,
 * "confidence": 0.93,
 * "parent_name": "植物",
 * "centric_score": 0.754
 * },
 * {
 * "name": "人",
 * "tag_level": 2,
 * "confidence": 0.923,
 * "parent_name": "面部",
 * "centric_score": 0.769
 * },
 * {
 * "name": "植物",
 * "tag_level": 1,
 * "confidence": 0.93,
 * "centric_score": 0.754
 * },
 * {
 * "name": "树林_森林",
 * "tag_level": 3,
 * "confidence": 0.921,
 * "parent_name": "自然景观",
 * "centric_score": 0.742
 * },
 * {
 * "name": "绿色",
 * "tag_level": 3,
 * "confidence": 0.909,
 * "parent_name": "颜色",
 * "centric_score": 0.754
 * },
 * {
 * "name": "T恤",
 * "tag_level": 2,
 * "confidence": 0.897,
 * "parent_name": "衣服",
 * "centric_score": 0.769
 * },
 * {
 * "name": "女孩",
 * "tag_level": 2,
 * "confidence": 0.884,
 * "parent_name": "面部",
 * "centric_score": 0.732
 * },
 * {
 * "name": "丛林",
 * "tag_level": 3,
 * "confidence": 0.882,
 * "parent_name": "自然景观",
 * "centric_score": 0.72
 * },
 * {
 * "name": "徒步",
 * "tag_level": 2,
 * "confidence": 0.881,
 * "parent_name": "体育运动",
 * "centric_score": 0.708
 * },
 * {
 * "name": "男性",
 * "tag_level": 2,
 * "confidence": 0.878,
 * "parent_name": "面部",
 * "centric_score": 0.763
 * },
 * {
 * "name": "女性",
 * "tag_level": 2,
 * "confidence": 0.864,
 * "parent_name": "面部",
 * "centric_score": 0.766
 * },
 * {
 * "name": "公园",
 * "tag_level": 2,
 * "confidence": 0.755,
 * "parent_name": "其他场景",
 * "centric_score": 0.754
 * },
 * {
 * "name": "男孩",
 * "tag_level": 2,
 * "confidence": 0.752,
 * "parent_name": "面部",
 * "centric_score": 0.671
 * },
 * {
 * "name": "林地",
 * "tag_level": 3,
 * "confidence": 0.724,
 * "parent_name": "自然景观",
 * "centric_score": 0.737
 * },
 * {
 * "name": "人像",
 * "tag_level": 2,
 * "confidence": 0.696,
 * "parent_name": "面部",
 * "centric_score": 0.76
 * },
 * {
 * "name": "衣服",
 * "tag_level": 1,
 * "confidence": 1,
 * "centric_score": 0.769
 * },
 * {
 * "name": "自然景观",
 * "tag_level": 2,
 * "confidence": 0.949,
 * "parent_name": "旅游&地理",
 * "centric_score": 0.742
 * },
 * {
 * "name": "年龄段",
 * "tag_level": 1,
 * "confidence": 0.933,
 * "centric_score": 0.667
 * },
 * {
 * "name": "面部",
 * "tag_level": 1,
 * "confidence": 0.923,
 * "centric_score": 0.769
 * },
 * {
 * "name": "颜色",
 * "tag_level": 2,
 * "confidence": 0.909,
 * "parent_name": "其他事物",
 * "centric_score": 0.754
 * },
 * {
 * "name": "体育运动",
 * "tag_level": 1,
 * "confidence": 0.881,
 * "centric_score": 0.708
 * },
 * {
 * "name": "其他场景",
 * "tag_level": 1,
 * "confidence": 0.755,
 * "centric_score": 0.754
 * },
 * {
 * "name": "旅游&地理",
 * "tag_level": 1,
 * "confidence": 0.949,
 * "centric_score": 0.742
 * },
 * {
 * "name": "其他事物",
 * "tag_level": 1,
 * "confidence": 0.909,
 * "centric_score": 0.754
 * }
 * ],
 * "faces": "[{\"FaceId\":\"807741b0-1216-47fb-abec-05cc252fb63c\",\"FaceConfidence\":0.942,\"GroupId\":\"figure-cluster-id-unavailable\",\"Age\":28,\"AgeSD\":12,\"Gender\":\"male\",\"GenderConfidence\":0.998,\"Emotion\":\"none\",\"EmotionConfidence\":0.994,\"FaceAttributes\":{\"FaceQuality\":0.3,\"FaceBoundary\":{\"Width\":79,\"Height\":106,\"Top\":1878,\"Left\":1384},\"Mouth\":\"close\",\"MouthConfidence\":0.978,\"Beard\":\"none\",\"BeardConfidence\":0.92,\"Hat\":\"none\",\"HatConfidence\":1,\"Mask\":\"none\",\"MaskConfidence\":0.741,\"Glasses\":\"none\",\"GlassesConfidence\":0.962,\"HeadPose\":{\"Pitch\":71.252,\"Roll\":-77.947,\"Yaw\":-63.513}}}]",
 * "exif": "{\"ApertureValue\":{\"value\":\"54823/32325\"},\"BrightnessValue\":{\"value\":\"131957/27071\"},\"ColorSpace\":{\"value\":\"65535\"},\"DateTime\":{\"value\":\"2023:09:15 13:03:10\"},\"DateTimeDigitized\":{\"value\":\"2023:09:15 13:03:10\"},\"DateTimeOriginal\":{\"value\":\"2023:09:15 13:03:10\"},\"ExifTag\":{\"value\":\"216\"},\"ExifVersion\":{\"value\":\"48 50 51 50\"},\"ExposureBiasValue\":{\"value\":\"0/1\"},\"ExposureMode\":{\"value\":\"0\"},\"ExposureProgram\":{\"value\":\"2\"},\"ExposureTime\":{\"value\":\"1/122\"},\"FNumber\":{\"value\":\"9/5\"},\"FileSize\":{\"value\":\"2493898\"},\"Flash\":{\"value\":\"16\"},\"FocalLength\":{\"value\":\"17/4\"},\"FocalLengthIn35mmFilm\":{\"value\":\"26\"},\"Format\":{\"value\":\"heic\"},\"FrameCount\":{\"value\":\"1\"},\"GPSAltitude\":{\"value\":\"84569/1348\"},\"GPSAltitudeRef\":{\"value\":\"0\"},\"GPSDateStamp\":{\"value\":\"2023:09:15\"},\"GPSDestBearing\":{\"value\":\"42311/2017\"},\"GPSDestBearingRef\":{\"value\":\"T\"},\"GPSImgDirection\":{\"value\":\"42311/2017\"},\"GPSImgDirectionRef\":{\"value\":\"T\"},\"GPSLatitude\":{\"value\":\"30deg 57' 32.300\\\" \"},\"GPSLatitudeRef\":{\"value\":\"North\"},\"GPSLongitude\":{\"value\":\"114deg 27' 18.140\\\" \"},\"GPSLongitudeRef\":{\"value\":\"East\"},\"GPSSpeed\":{\"value\":\"24535/186139\"},\"GPSSpeedRef\":{\"value\":\"K\"},\"GPSTag\":{\"value\":\"2132\"},\"HostComputer\":{\"value\":\"iPhone 11\"},\"ISOSpeedRatings\":{\"value\":\"50\"},\"ImageHeight\":{\"value\":\"4032\"},\"ImageWidth\":{\"value\":\"3024\"},\"LensMake\":{\"value\":\"Apple\"},\"LensModel\":{\"value\":\"iPhone 11 back dual wide camera 4.25mm f/1.8\"},\"LensSpecification\":{\"value\":\"807365/524263 17/4 9/5 12/5\"},\"Make\":{\"value\":\"Apple\"},\"MakerNote\":{\"value\":\"3315 bytes undefined data\"},\"MeteringMode\":{\"value\":\"5\"},\"Model\":{\"value\":\"iPhone 11\"},\"Orientation\":{\"value\":\"6\"},\"PixelXDimension\":{\"value\":\"4032\"},\"PixelYDimension\":{\"value\":\"3024\"},\"ResolutionUnit\":{\"value\":\"2\"},\"SceneType\":{\"value\":\"1\"},\"SensingMethod\":{\"value\":\"2\"},\"ShutterSpeedValue\":{\"value\":\"95435/13779\"},\"Software\":{\"value\":\"16.1.1\"},\"SubSecTimeDigitized\":{\"value\":\"972\"},\"SubSecTimeOriginal\":{\"value\":\"972\"},\"SubjectArea\":{\"value\":\"2006 1507 2315 1388\"},\"WhiteBalance\":{\"value\":\"0\"},\"XResolution\":{\"value\":\"72/1\"},\"YResolution\":{\"value\":\"72/1\"}}",
 * "image_quality": {
 * "overall_score": 0.673
 * }
 * },
 * "punish_flag": 0,
 * "meta_name_punish_flag": 0,
 * "meta_name_investigation_status": 0,
 * "creator_type": "User",
 * "creator_id": "0075833233234dd187c395ee3c7747b0",
 * "last_modifier_type": "User",
 * "last_modifier_id": "0075833233234dd187c395ee3c7747b0",
 * "user_tags": {
 * "channel": "file_upload",
 * "client": "windows",
 * "device_id": "36fb6f9df2c4dfb53bff0d0f1699d3eebaafe02b67554d7f493298e03d8f1437",
 * "device_name": "LAPTOP-MCCB4FFK",
 * "exif_make": "Apple",
 * "exif_model": "iPhone 11",
 * "exif_software": "16.1.1",
 * "version": "v4.13.1"
 * },
 * "revision_id": "65f89806fa0d6ca6fe2946bc986f7c2c0808ce12",
 * "revision_version": 1,
 * "sync_flag": false,
 * "sync_device_flag": false,
 * "sync_meta": "",
 * "trashed": false
 * }
 */

/**
 *  带预览图视频
 {
 "drive_id": "18654654",
 "domain_id": "bj29",
 "file_id": "65f8980ad90d7bcea264475ea8abce0aaa9ae012",
 "name": "IMG_2506.MOV",
 "type": "file",
 "content_type": "application/oct-stream",
 "created_at": "2024-03-18T19:37:46.805Z",
 "updated_at": "2024-03-18T19:37:46.805Z",
 "file_extension": "mov",
 "mime_type": "video/quicktime",
 "mime_extension": "mov",
 "hidden": false,
 "size": 2811735,
 "starred": false,
 "status": "available",
 "user_meta": "{\"channel\":\"file_upload\",\"client\":\"windows\"}",
 "upload_id": "rapid-112f6a70-2e78-49eb-adaa-929546381740",
 "parent_file_id": "65f88c65d21a7dd760f04d5f8336b8058d4ab6f5",
 "crc64_hash": "15273709128597645316",
 "content_hash": "4D4ADDC2C88F156B7AE9C3CB92ADA50A04796CB5",
 "content_hash_name": "sha1",
 "download_url": "",
 "url": "https://cn-beijing-data.aliyundrive.net/gDghRXuN%2F18654654%2F65f88c6150cda47812bd4ca4b8744eb70d09451b%2F65f88c61a73950d5a80f47698d01b01fa3a992ad?callback=eyJjYWxsYmFja1VybCI6Imh0dHA6Ly9iajI5LmFwaS1ocC5hbGl5dW5wZHMuY29tL3YyL2ZpbGUvZG93bmxvYWRfY2FsbGJhY2siLCJjYWxsYmFja0JvZHkiOiJodHRwSGVhZGVyLnJhbmdlPSR7aHR0cEhlYWRlci5yYW5nZX1cdTAwMjZidWNrZXQ9JHtidWNrZXR9XHUwMDI2b2JqZWN0PSR7b2JqZWN0fVx1MDAyNmRvbWFpbl9pZD0ke3g6ZG9tYWluX2lkfVx1MDAyNnVzZXJfaWQ9JHt4OnVzZXJfaWR9XHUwMDI2ZHJpdmVfaWQ9JHt4OmRyaXZlX2lkfVx1MDAyNmZpbGVfaWQ9JHt4OmZpbGVfaWR9XHUwMDI2cGRzX3BhcmFtcz0ke3g6cGRzX3BhcmFtc31cdTAwMjZ2ZXJzaW9uPSR7eDp2ZXJzaW9ufSIsImNhbGxiYWNrQm9keVR5cGUiOiJhcHBsaWNhdGlvbi94LXd3dy1mb3JtLXVybGVuY29kZWQiLCJjYWxsYmFja1N0YWdlIjoiYmVmb3JlLWV4ZWN1dGUiLCJjYWxsYmFja0ZhaWx1cmVBY3Rpb24iOiJpZ25vcmUifQ%3D%3D&callback-var=eyJ4OmRvbWFpbl9pZCI6ImJqMjkiLCJ4OnVzZXJfaWQiOiIwMDc1ODlkNzczMzk0ZGQxODdjMzk1ZWUzYzc3NDdiMCIsIng6ZHJpdmVfaWQiOiIxMzI5ODY1MCIsIng6ZmlsZV9pZCI6IjY1Zjg5ODBhZDkwZDdiY2VhMjY0NDc1ZWE4YWJjZTBhYWE5YWUwMTIiLCJ4OnBkc19wYXJhbXMiOiJ7XCJhcFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwifSIsIng6dmVyc2lvbiI6InYzIn0%3D&di=bj29&dr=18654654&f=65f8980ad90d7bcea264475ea8abce0aaa9ae012&pds-params=%7B%22ap%22%3A%2225dzX3vbYqktVxyX%22%7D&response-content-disposition=attachment%3B%20filename%2A%3DUTF-8%27%27&security-token=CAISvgJ1q6Ft5B2yfSjIr5b2KNDO2opJ%2FPOlcxHrnlQDZ%2BUZhIaY0zz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNSiBEVfMqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYne0yJq1B5bCbP7AhWvDNQ3S7jN6YihvSt3zmA4YsrdqJPW1dKDogPIx4aBwHbHMFKlwddMkwuiM3K2Z%2FXtuMkagAE%2BSoF5x5Z7GN65GySrWKUVuFeP9SpNK1m5wKsz40t1GZK3n0XBmClsQvdxMKzG%2Fy%2B%2FMfs6dyFIL%2FAR3P1jFvS8civ0ZGuOAabcC0zhJ9xqQDyznxGu0uDlX%2F6WyoZGDh3nN6pXyeBnaa2DpASd%2BuYxSmKHc4%2B6HTe3UXM9ilih3iAA&u=0075833233234dd187c395ee3c7747b0&x-oss-access-key-id=STS.NUCcjz7UhK1Nq7ZzTVkj5kC31&x-oss-additional-headers=referer&x-oss-expires=1714461995&x-oss-signature=jJYgua1VIVxEtIZp57eDuXQWE%2FQXUvNw16I2xhh%2FSVg%3D&x-oss-signature-version=OSS2",
 "thumbnail": "https://cn-beijing-data.aliyundrive.net/gDghRXuN%2F18654654%2F65f88c6150cda47812bd4ca4b8744eb70d09451b%2F65f88c61a73950d5a80f47698d01b01fa3a992ad?security-token=CAISvgJ1q6Ft5B2yfSjIr5b2KNDO2opJ%2FPOlcxHrnlQDZ%2BUZhIaY0zz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNSiBEVfMqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYne0yJq1B5bCbP7AhWvDNQ3S7jN6YihvSt3zmA4YsrdqJPW1dKDogPIx4aBwHbHMFKlwddMkwuiM3K2Z%2FXtuMkagAE%2BSoF5x5Z7GN65GySrWKUVuFeP9SpNK1m5wKsz40t1GZK3n0XBmClsQvdxMKzG%2Fy%2B%2FMfs6dyFIL%2FAR3P1jFvS8civ0ZGuOAabcC0zhJ9xqQDyznxGu0uDlX%2F6WyoZGDh3nN6pXyeBnaa2DpASd%2BuYxSmKHc4%2B6HTe3UXM9ilih3iAA&x-oss-access-key-id=STS.NUCcjz7UhK1Nq7ZzTVkj5kC31&x-oss-additional-headers=referer&x-oss-expires=1714461995&x-oss-process=video%2Fsnapshot%2Ct_120000%2Cf_jpg%2Cm_lfit%2Cw_256%2Car_auto%2Cm_fast&x-oss-signature=0vpdi7AMd98bm6BbMYdkZ%2BXPZbV2PQzbFrcUKLiXYJs%3D&x-oss-signature-version=OSS2",
 "category": "video",
 "encrypt_mode": "none",
 "video_media_metadata": {
 "time": "2023-04-08T07:03:31.000Z",
 "width": 1920,
 "height": 1440,
 "location": "",
 "country": "",
 "province": "",
 "city": "",
 "district": "",
 "township": "",
 "address_line": "",
 "video_media_video_stream": [
 {
 "duration": "1.733333",
 "clarity": "1440",
 "fps": "11400/533",
 "bitrate": "11911001",
 "code_name": "hevc",
 "frame_count": "38"
 }
 ],
 "video_media_audio_stream": [
 {
 "duration": "1.731678",
 "channels": 1,
 "channel_layout": "",
 "bit_rate": "705600",
 "code_name": "pcm_s16le",
 "sample_rate": "44100"
 }
 ],
 "duration": "1.733333"
 },
 "punish_flag": 0,
 "meta_name_punish_flag": 0,
 "meta_name_investigation_status": 0,
 "creator_type": "User",
 "creator_id": "0075833233234dd187c395ee3c7747b0",
 "last_modifier_type": "User",
 "last_modifier_id": "0075833233234dd187c395ee3c7747b0",
 "user_tags": {
 "channel": "file_upload",
 "client": "windows",
 "device_id": "36fb6f9df2c4dfb53bff0d0f1699d3eebaafe02b67554d7f493298e03d8f1437",
 "device_name": "LAPTOP-MCCB4FFK",
 "version": "v4.13.1"
 },
 "revision_id": "65f8980adba925c63a5242c288fd2315148164bd",
 "revision_version": 1,
 "sync_flag": false,
 "sync_device_flag": false,
 "sync_meta": "",
 "trashed": false
 }
 */

/**
 * 图片
 {
 "drive_id": "18654654",
 "domain_id": "bj29",
 "file_id": "65f898067fc32b4d938d481fbbc618972d63d3d7",
 "name": "IMG_6176.HEIC",
 "type": "file",
 "content_type": "application/oct-stream",
 "created_at": "2024-03-18T19:37:42.118Z",
 "updated_at": "2024-03-18T19:37:42.118Z",
 "file_extension": "heic",
 "mime_type": "image/heic",
 "mime_extension": "heic",
 "hidden": false,
 "size": 2493898,
 "starred": false,
 "status": "available",
 "user_meta": "{\"channel\":\"file_upload\",\"client\":\"windows\"}",
 "labels": [
 "植物",
 "其他事物",
 "旅游&地理",
 "体育运动",
 "面部",
 "衣服",
 "T恤",
 "女孩",
 "徒步",
 "男性",
 "女性",
 "颜色",
 "服装",
 "树",
 "自然景观",
 "人",
 "丛林",
 "森林",
 "绿色",
 "树林_森林"
 ],
 "upload_id": "rapid-af153238-8108-402f-82e5-49b8bf6c11b1",
 "parent_file_id": "65f88c65d21a7dd760f04d5f8336b8058d4ab6f5",
 "crc64_hash": "6136284431558099613",
 "content_hash": "39FA7F1B366D3A98E145CC6BC02383DBC645FFFB",
 "content_hash_name": "sha1",
 "download_url": "",
 "url": "https://cn-beijing-data.aliyundrive.net/3OTZIorN%2F18654654%2F65f88c5590379015e9564ba6852a9bd555dea2b9%2F65f88c555baafd0eba1a47c789cd30a931c12d60?security-token=CAISvgJ1q6Ft5B2yfSjIr5b0Gu%2FZv5lP1vOvM0PgnEc%2FfeFCl4b40Dz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXTee1fMqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYneg%2FLe1lOHLuHwufJ7FxfIREfquk63pvSlHLcLPe0Kjzzleo2k1XRPVFF%2B535IaHXuToXDnvSiMFBT%2FfXtuMkagAGCs8RA3Qu5KIXgtgK2XhwxPJwf7dB2VGq1o%2FnQvLV2o8SFqtiuqxXCnbtsYigEgxIBwVnLcOucU3fH1RAFoBwOr2sZzg5A9xJ5CIy97ghnYiR8rAKQbgzUNCO7ciO0H%2FpjRXW5hROGCjKqIXNGrT6TxDAvL3ByJwBJAG3kQu8TOSAA&x-oss-access-key-id=STS.NUAQUmRFna1D1eQxGjqnnxCS2&x-oss-additional-headers=referer&x-oss-expires=1714462432&x-oss-process=image%2Fresize%2Cw_1920%2Fformat%2Cavif&x-oss-signature=YUp95rZH9rKhax8lP%2BfpYHkOdMTuj48ohu5jkJtxg6A%3D&x-oss-signature-version=OSS2",
 "thumbnail": "https://cn-beijing-data.aliyundrive.net/3OTZIorN%2F18654654%2F65f88c5590379015e9564ba6852a9bd555dea2b9%2F65f88c555baafd0eba1a47c789cd30a931c12d60?security-token=CAISvgJ1q6Ft5B2yfSjIr5b0Gu%2FZv5lP1vOvM0PgnEc%2FfeFCl4b40Dz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXTee1fMqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYneg%2FLe1lOHLuHwufJ7FxfIREfquk63pvSlHLcLPe0Kjzzleo2k1XRPVFF%2B535IaHXuToXDnvSiMFBT%2FfXtuMkagAGCs8RA3Qu5KIXgtgK2XhwxPJwf7dB2VGq1o%2FnQvLV2o8SFqtiuqxXCnbtsYigEgxIBwVnLcOucU3fH1RAFoBwOr2sZzg5A9xJ5CIy97ghnYiR8rAKQbgzUNCO7ciO0H%2FpjRXW5hROGCjKqIXNGrT6TxDAvL3ByJwBJAG3kQu8TOSAA&x-oss-access-key-id=STS.NUAQUmRFna1D1eQxGjqnnxCS2&x-oss-additional-headers=referer&x-oss-expires=1714462432&x-oss-process=image%2Fresize%2Cw_256%2Fformat%2Cavif&x-oss-signature=GNWYYDloEaqnzWzdx9VU2VYWFPhveZC6A%2F%2F5YcWKYPk%3D&x-oss-signature-version=OSS2",
 "category": "image",
 "encrypt_mode": "none",
 "image_media_metadata": {
 "time": "2023-09-15T05:03:10.000Z",
 "width": 4032,
 "height": 3024,
 "location": "",
 "country": "",
 "province": "",
 "city": "",
 "district": "",
 "township": "",
 "address_line": "",
 "image_tags": [
 {
 "name": "服装",
 "tag_level": 2,
 "confidence": 1,
 "parent_name": "衣服",
 "centric_score": 0.752
 },
 {
 "name": "球鞋",
 "tag_level": 2,
 "confidence": 1,
 "parent_name": "衣服",
 "centric_score": 0.669
 },
 {
 "name": "鞋",
 "tag_level": 2,
 "confidence": 0.949,
 "parent_name": "衣服",
 "centric_score": 0.668
 },
 {
 "name": "森林",
 "tag_level": 3,
 "confidence": 0.949,
 "parent_name": "自然景观",
 "centric_score": 0.725
 },
 {
 "name": "牛仔裤",
 "tag_level": 2,
 "confidence": 0.941,
 "parent_name": "衣服",
 "centric_score": 0.675
 },
 {
 "name": "鞋类",
 "tag_level": 2,
 "confidence": 0.941,
 "parent_name": "衣服",
 "centric_score": 0.667
 },
 {
 "name": "幼儿",
 "tag_level": 2,
 "confidence": 0.933,
 "parent_name": "年龄段",
 "centric_score": 0.667
 },
 {
 "name": "树",
 "tag_level": 2,
 "confidence": 0.93,
 "parent_name": "植物",
 "centric_score": 0.754
 },
 {
 "name": "人",
 "tag_level": 2,
 "confidence": 0.923,
 "parent_name": "面部",
 "centric_score": 0.769
 },
 {
 "name": "植物",
 "tag_level": 1,
 "confidence": 0.93,
 "centric_score": 0.754
 },
 {
 "name": "树林_森林",
 "tag_level": 3,
 "confidence": 0.921,
 "parent_name": "自然景观",
 "centric_score": 0.742
 },
 {
 "name": "绿色",
 "tag_level": 3,
 "confidence": 0.909,
 "parent_name": "颜色",
 "centric_score": 0.754
 },
 {
 "name": "T恤",
 "tag_level": 2,
 "confidence": 0.897,
 "parent_name": "衣服",
 "centric_score": 0.769
 },
 {
 "name": "女孩",
 "tag_level": 2,
 "confidence": 0.884,
 "parent_name": "面部",
 "centric_score": 0.732
 },
 {
 "name": "丛林",
 "tag_level": 3,
 "confidence": 0.882,
 "parent_name": "自然景观",
 "centric_score": 0.72
 },
 {
 "name": "徒步",
 "tag_level": 2,
 "confidence": 0.881,
 "parent_name": "体育运动",
 "centric_score": 0.708
 },
 {
 "name": "男性",
 "tag_level": 2,
 "confidence": 0.878,
 "parent_name": "面部",
 "centric_score": 0.763
 },
 {
 "name": "女性",
 "tag_level": 2,
 "confidence": 0.864,
 "parent_name": "面部",
 "centric_score": 0.766
 },
 {
 "name": "公园",
 "tag_level": 2,
 "confidence": 0.755,
 "parent_name": "其他场景",
 "centric_score": 0.754
 },
 {
 "name": "男孩",
 "tag_level": 2,
 "confidence": 0.752,
 "parent_name": "面部",
 "centric_score": 0.671
 },
 {
 "name": "林地",
 "tag_level": 3,
 "confidence": 0.724,
 "parent_name": "自然景观",
 "centric_score": 0.737
 },
 {
 "name": "人像",
 "tag_level": 2,
 "confidence": 0.696,
 "parent_name": "面部",
 "centric_score": 0.76
 },
 {
 "name": "衣服",
 "tag_level": 1,
 "confidence": 1,
 "centric_score": 0.769
 },
 {
 "name": "自然景观",
 "tag_level": 2,
 "confidence": 0.949,
 "parent_name": "旅游&地理",
 "centric_score": 0.742
 },
 {
 "name": "年龄段",
 "tag_level": 1,
 "confidence": 0.933,
 "centric_score": 0.667
 },
 {
 "name": "面部",
 "tag_level": 1,
 "confidence": 0.923,
 "centric_score": 0.769
 },
 {
 "name": "颜色",
 "tag_level": 2,
 "confidence": 0.909,
 "parent_name": "其他事物",
 "centric_score": 0.754
 },
 {
 "name": "体育运动",
 "tag_level": 1,
 "confidence": 0.881,
 "centric_score": 0.708
 },
 {
 "name": "其他场景",
 "tag_level": 1,
 "confidence": 0.755,
 "centric_score": 0.754
 },
 {
 "name": "旅游&地理",
 "tag_level": 1,
 "confidence": 0.949,
 "centric_score": 0.742
 },
 {
 "name": "其他事物",
 "tag_level": 1,
 "confidence": 0.909,
 "centric_score": 0.754
 }
 ],
 "faces": "[{\"FaceId\":\"807741b0-1216-47fb-abec-05cc252fb63c\",\"FaceConfidence\":0.942,\"GroupId\":\"figure-cluster-id-unavailable\",\"Age\":28,\"AgeSD\":12,\"Gender\":\"male\",\"GenderConfidence\":0.998,\"Emotion\":\"none\",\"EmotionConfidence\":0.994,\"FaceAttributes\":{\"FaceQuality\":0.3,\"FaceBoundary\":{\"Width\":79,\"Height\":106,\"Top\":1878,\"Left\":1384},\"Mouth\":\"close\",\"MouthConfidence\":0.978,\"Beard\":\"none\",\"BeardConfidence\":0.92,\"Hat\":\"none\",\"HatConfidence\":1,\"Mask\":\"none\",\"MaskConfidence\":0.741,\"Glasses\":\"none\",\"GlassesConfidence\":0.962,\"HeadPose\":{\"Pitch\":71.252,\"Roll\":-77.947,\"Yaw\":-63.513}}}]",
 "exif": "{\"ApertureValue\":{\"value\":\"54823/32325\"},\"BrightnessValue\":{\"value\":\"131957/27071\"},\"ColorSpace\":{\"value\":\"65535\"},\"DateTime\":{\"value\":\"2023:09:15 13:03:10\"},\"DateTimeDigitized\":{\"value\":\"2023:09:15 13:03:10\"},\"DateTimeOriginal\":{\"value\":\"2023:09:15 13:03:10\"},\"ExifTag\":{\"value\":\"216\"},\"ExifVersion\":{\"value\":\"48 50 51 50\"},\"ExposureBiasValue\":{\"value\":\"0/1\"},\"ExposureMode\":{\"value\":\"0\"},\"ExposureProgram\":{\"value\":\"2\"},\"ExposureTime\":{\"value\":\"1/122\"},\"FNumber\":{\"value\":\"9/5\"},\"FileSize\":{\"value\":\"2493898\"},\"Flash\":{\"value\":\"16\"},\"FocalLength\":{\"value\":\"17/4\"},\"FocalLengthIn35mmFilm\":{\"value\":\"26\"},\"Format\":{\"value\":\"heic\"},\"FrameCount\":{\"value\":\"1\"},\"GPSAltitude\":{\"value\":\"84569/1348\"},\"GPSAltitudeRef\":{\"value\":\"0\"},\"GPSDateStamp\":{\"value\":\"2023:09:15\"},\"GPSDestBearing\":{\"value\":\"42311/2017\"},\"GPSDestBearingRef\":{\"value\":\"T\"},\"GPSImgDirection\":{\"value\":\"42311/2017\"},\"GPSImgDirectionRef\":{\"value\":\"T\"},\"GPSLatitude\":{\"value\":\"30deg 57' 32.300\\\" \"},\"GPSLatitudeRef\":{\"value\":\"North\"},\"GPSLongitude\":{\"value\":\"114deg 27' 18.140\\\" \"},\"GPSLongitudeRef\":{\"value\":\"East\"},\"GPSSpeed\":{\"value\":\"24535/186139\"},\"GPSSpeedRef\":{\"value\":\"K\"},\"GPSTag\":{\"value\":\"2132\"},\"HostComputer\":{\"value\":\"iPhone 11\"},\"ISOSpeedRatings\":{\"value\":\"50\"},\"ImageHeight\":{\"value\":\"4032\"},\"ImageWidth\":{\"value\":\"3024\"},\"LensMake\":{\"value\":\"Apple\"},\"LensModel\":{\"value\":\"iPhone 11 back dual wide camera 4.25mm f/1.8\"},\"LensSpecification\":{\"value\":\"807365/524263 17/4 9/5 12/5\"},\"Make\":{\"value\":\"Apple\"},\"MakerNote\":{\"value\":\"3315 bytes undefined data\"},\"MeteringMode\":{\"value\":\"5\"},\"Model\":{\"value\":\"iPhone 11\"},\"Orientation\":{\"value\":\"6\"},\"PixelXDimension\":{\"value\":\"4032\"},\"PixelYDimension\":{\"value\":\"3024\"},\"ResolutionUnit\":{\"value\":\"2\"},\"SceneType\":{\"value\":\"1\"},\"SensingMethod\":{\"value\":\"2\"},\"ShutterSpeedValue\":{\"value\":\"95435/13779\"},\"Software\":{\"value\":\"16.1.1\"},\"SubSecTimeDigitized\":{\"value\":\"972\"},\"SubSecTimeOriginal\":{\"value\":\"972\"},\"SubjectArea\":{\"value\":\"2006 1507 2315 1388\"},\"WhiteBalance\":{\"value\":\"0\"},\"XResolution\":{\"value\":\"72/1\"},\"YResolution\":{\"value\":\"72/1\"}}",
 "image_quality": {
 "overall_score": 0.673
 }
 },
 "punish_flag": 0,
 "meta_name_punish_flag": 0,
 "meta_name_investigation_status": 0,
 "creator_type": "User",
 "creator_id": "0075833233234dd187c395ee3c7747b0",
 "last_modifier_type": "User",
 "last_modifier_id": "0075833233234dd187c395ee3c7747b0",
 "user_tags": {
 "channel": "file_upload",
 "client": "windows",
 "device_id": "36fb6f9df2c4dfb53bff0d0f1699d3eebaafe02b67554d7f493298e03d8f1437",
 "device_name": "LAPTOP-MCCB4FFK",
 "exif_make": "Apple",
 "exif_model": "iPhone 11",
 "exif_software": "16.1.1",
 "version": "v4.13.1"
 },
 "revision_id": "65f89806fa0d6ca6fe2946bc986f7c2c0808ce12",
 "revision_version": 1,
 "sync_flag": false,
 "sync_device_flag": false,
 "sync_meta": "",
 "trashed": false
 }
 * */

/**
 * 文件信息
 *
 * @author weiba
 * @date 2024/4/30 11:13
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetFileInfoResponse {

    @JSONField(name = "drive_id")
    private String driveId;
    @JSONField(name = "domain_id")
    private String domainId;
    @JSONField(name = "file_id")
    private String fileId;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "content_type")
    private String contentType;
    @JSONField(name = "created_at")
    private String createdAt;
    @JSONField(name = "updated_at")
    private String updatedAt;
    @JSONField(name = "file_extension")
    private String fileExtension;
    @JSONField(name = "mime_type")
    private String mimeType;
    @JSONField(name = "mime_extension")
    private String mimeExtension;
    @JSONField(name = "hidden")
    private Boolean hidden;
    @JSONField(name = "size")
    private Integer size;
    @JSONField(name = "starred")
    private Boolean starred;
    @JSONField(name = "status")
    private String status;
    @JSONField(name = "user_meta")
    private String userMeta;
    @JSONField(name = "upload_id")
    private String uploadId;
    @JSONField(name = "parent_file_id")
    private String parentFileId;
    @JSONField(name = "crc64_hash")
    private String crc64Hash;
    @JSONField(name = "content_hash")
    private String contentHash;
    @JSONField(name = "content_hash_name")
    private String contentHashName;
    @JSONField(name = "download_url")
    private String downloadUrl;
    @JSONField(name = "url")
    private String url;
    @JSONField(name = "thumbnail")
    private String thumbnail;
    @JSONField(name = "category")
    private String category;
    @JSONField(name = "encrypt_mode")
    private String encryptMode;
    @JSONField(name = "video_media_metadata")
    private VideoMediaMetadataDTO videoMediaMetadata;
    @JSONField(name = "punish_flag")
    private Integer punishFlag;
    @JSONField(name = "meta_name_punish_flag")
    private Integer metaNamePunishFlag;
    @JSONField(name = "meta_name_investigation_status")
    private Integer metaNameInvestigationStatus;
    @JSONField(name = "creator_type")
    private String creatorType;
    @JSONField(name = "creator_id")
    private String creatorId;
    @JSONField(name = "last_modifier_type")
    private String lastModifierType;
    @JSONField(name = "last_modifier_id")
    private String lastModifierId;
    @JSONField(name = "user_tags")
    private UserTagsDTO userTags;
    @JSONField(name = "revision_id")
    private String revisionId;
    @JSONField(name = "revision_version")
    private Integer revisionVersion;
    @JSONField(name = "sync_flag")
    private Boolean syncFlag;
    @JSONField(name = "sync_device_flag")
    private Boolean syncDeviceFlag;
    @JSONField(name = "sync_meta")
    private String syncMeta;
    @JSONField(name = "trashed")
    private Boolean trashed;
    @JSONField(name = "labels")
    private List<String> labels;
    @JSONField(name = "image_media_metadata")
    private ImageMediaMetadataDTO imageMediaMetadata;

    @NoArgsConstructor
    @Data
    public static class userMetaDTO {

        /**
         * channel
         */
        @JSONField(name = "channel")
        private String channel;
        /**
         * client
         */
        @JSONField(name = "client")
        private String client;
    }

    @NoArgsConstructor
    @Data
    public static class VideoMediaMetadataDTO {
        @JSONField(name = "time")
        private String time;
        @JSONField(name = "width")
        private Integer width;
        @JSONField(name = "height")
        private Integer height;
        @JSONField(name = "location")
        private String location;
        @JSONField(name = "country")
        private String country;
        @JSONField(name = "province")
        private String province;
        @JSONField(name = "city")
        private String city;
        @JSONField(name = "district")
        private String district;
        @JSONField(name = "township")
        private String township;
        @JSONField(name = "address_line")
        private String addressLine;
        @JSONField(name = "video_media_video_stream")
        private List<VideoMediaVideoStreamDTO> videoMediaVideoStream;
        @JSONField(name = "video_media_audio_stream")
        private List<VideoMediaAudioStreamDTO> videoMediaAudioStream;
        @JSONField(name = "duration")
        private String duration;

        @NoArgsConstructor
        @Data
        public static class VideoMediaVideoStreamDTO {
            @JSONField(name = "duration")
            private String duration;
            @JSONField(name = "clarity")
            private String clarity;
            @JSONField(name = "fps")
            private String fps;
            @JSONField(name = "bitrate")
            private String bitrate;
            @JSONField(name = "code_name")
            private String codeName;
            @JSONField(name = "frame_count")
            private String frameCount;
        }

        @NoArgsConstructor
        @Data
        public static class VideoMediaAudioStreamDTO {
            @JSONField(name = "duration")
            private String duration;
            @JSONField(name = "channels")
            private Integer channels;
            @JSONField(name = "channel_layout")
            private String channelLayout;
            @JSONField(name = "bit_rate")
            private String bitRate;
            @JSONField(name = "code_name")
            private String codeName;
            @JSONField(name = "sample_rate")
            private String sampleRate;
        }
    }


    @NoArgsConstructor
    @Data
    public static class UserTagsDTO {
        @JSONField(name = "channel")
        private String channel;
        @JSONField(name = "client")
        private String client;
        @JSONField(name = "device_id")
        private String deviceId;
        @JSONField(name = "device_name")
        private String deviceName;
        @JSONField(name = "exif_make")
        private String exifMake;
        @JSONField(name = "exif_model")
        private String exifModel;
        @JSONField(name = "exif_software")
        private String exifSoftware;
        @JSONField(name = "version")
        private String version;
    }

    @NoArgsConstructor
    @Data
    public static class ImageMediaMetadataDTO {
        @JSONField(name = "time")
        private String time;
        @JSONField(name = "width")
        private Integer width;
        @JSONField(name = "height")
        private Integer height;
        @JSONField(name = "location")
        private String location;
        @JSONField(name = "country")
        private String country;
        @JSONField(name = "province")
        private String province;
        @JSONField(name = "city")
        private String city;
        @JSONField(name = "district")
        private String district;
        @JSONField(name = "township")
        private String township;
        @JSONField(name = "address_line")
        private String addressLine;
        @JSONField(name = "image_tags")
        private List<ImageTagsDTO> imageTags;
        @JSONField(name = "faces")
        private String faces;
        @JSONField(name = "exif")
        private String exif;
        @JSONField(name = "image_quality")
        private ImageQualityDTO imageQuality;

        @NoArgsConstructor
        @Data
        public static class ImageQualityDTO {
            @JSONField(name = "overall_score")
            private Double overallScore;
        }

        @NoArgsConstructor
        @Data
        public static class ImageTagsDTO {
            @JSONField(name = "name")
            private String nameX;
            @JSONField(name = "tag_level")
            private Integer tagLevel;
            @JSONField(name = "confidence")
            private Integer confidence;
            @JSONField(name = "parent_name")
            private String parentName;
            @JSONField(name = "centric_score")
            private Double centricScore;
        }
    }
}
