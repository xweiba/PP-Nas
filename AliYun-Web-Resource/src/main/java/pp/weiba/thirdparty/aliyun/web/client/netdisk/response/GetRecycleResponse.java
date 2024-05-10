package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/*
{
    "items": [
        {
            "name": "Fiddler Everywhere 3.4.1.exe",
            "type": "file",
            "category": "others",
            "hidden": false,
            "status": "available",
            "url": "https://cn-beijing-data.aliyundrive.net/KrN8OZEr%2F35945053%2F6316e7d057c9805fcef446d3aa82d50a9e94c1a3%2F6316e7d03116e93d2f1e44b6942f8fe64d001548?callback=eyJjYWxsYmFja1VybCI6Imh0dHA6Ly9iajI5LmFwaS1ocC5hbGl5dW5wZHMuY29tL3YyL2ZpbGUvZG93bmxvYWRfY2FsbGJhY2siLCJjYWxsYmFja0JvZHkiOiJodHRwSGVhZGVyLnJhbmdlPSR7aHR0cEhlYWRlci5yYW5nZX1cdTAwMjZidWNrZXQ9JHtidWNrZXR9XHUwMDI2b2JqZWN0PSR7b2JqZWN0fVx1MDAyNmRvbWFpbl9pZD0ke3g6ZG9tYWluX2lkfVx1MDAyNnVzZXJfaWQ9JHt4OnVzZXJfaWR9XHUwMDI2ZHJpdmVfaWQ9JHt4OmRyaXZlX2lkfVx1MDAyNmZpbGVfaWQ9JHt4OmZpbGVfaWR9XHUwMDI2cGRzX3BhcmFtcz0ke3g6cGRzX3BhcmFtc31cdTAwMjZ2ZXJzaW9uPSR7eDp2ZXJzaW9ufSIsImNhbGxiYWNrQm9keVR5cGUiOiJhcHBsaWNhdGlvbi94LXd3dy1mb3JtLXVybGVuY29kZWQiLCJjYWxsYmFja1N0YWdlIjoiYmVmb3JlLWV4ZWN1dGUiLCJjYWxsYmFja0ZhaWx1cmVBY3Rpb24iOiJpZ25vcmUifQ%3D%3D&callback-var=eyJ4OmRvbWFpbl9pZCI6ImJqMjkiLCJ4OnVzZXJfaWQiOiIwMDc1ODlkNzczMzk0ZGQxODdjMzk1ZWUzYzc3NDdiMCIsIng6ZHJpdmVfaWQiOiIxMzI5ODY1MCIsIng6ZmlsZV9pZCI6IjYzNDExZDg5ZmU2MzFlOTc0NWJkNGNmNDgzMGQ1ZTJhNWVlMjU4MDUiLCJ4OnBkc19wYXJhbXMiOiJ7XCJhcFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwifSIsIng6dmVyc2lvbiI6InYzIn0%3D&di=bj29&dr=18654654&f=63411d89fe631e9745bd4cf4830d5e2a5ee25805&pds-params=%7B%22ap%22%3A%2225dzX3vbYqktVxyX%22%7D&response-content-disposition=attachment%3B%20filename%2A%3DUTF-8%27%27&security-token=CAISvgJ1q6Ft5B2yfSjIr5b2Lo%2FEhrVV2vu6MEWF1nkEdsMfhLLKqDz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXnMQz3JqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYnegzlXUhv%2F64IClLcc%2BmqdsRIvJzWstJ7Gf9LWqChvSgk4TxhhcNFKSTQrInFCB0%2BcRObJl16iIMNtGfXtuMkagAGle5xT2tewhVclS52PQHV74iceX3r4mDTZgdDyxmyScUn49S3trQEJQCFDnqQ96lQKflCLKixHjT%2BLoLThoI9AK1bxZN3Xm%2BfZ63WgYESqKAtbW2lfx0yBLnmLK%2Fi6ov6UBCvOqsm817fH08gZq5PP9aDFoPU73zzDKcJmm1HvliAA&u=0075833233234dd187c395ee3c7747b0&x-oss-access-key-id=STS.NUCe5pkjtm9Q2c42yQzL3kwaJ&x-oss-additional-headers=referer&x-oss-expires=1715323056&x-oss-signature=U2k7qZoGj0FCn%2B%2BUV%2Fyst3OKPY9Vs9gdqL4XjBrHyB4%3D&x-oss-signature-version=OSS2",
            "size": 113728768,
            "starred": false,
            "parent_file_id": "recyclebin",
            "drive_id": "18654654",
            "file_id": "63411d89fe631e9745bd4cf4830d5e2a5ee25805",
            "file_extension": "exe",
            "content_hash": "866860EC90DD7A44A75D28A0514568642E9B12A4",
            "content_hash_name": "sha1",
            "encrypt_mode": "none",
            "domain_id": "bj29",
            "created_at": "2022-10-08T06:49:45.396Z",
            "updated_at": "2024-05-10T06:22:22.697Z",
            "trashed_at": "2024-05-10T06:22:22.697Z",
            "punish_flag": 0,
            "gmt_expired": "2024-05-20T06:22:22.650Z"
        },
        {
            "name": "mark (66).png",
            "thumbnail": "https://cn-beijing-data.aliyundrive.net/5hp3lmWZ%2F18654654%2F663dbb87a45ed768219f465a82ab7a4f9d337967%2F663dbb87fb1d942444cf44abb8e04eda74fc88e2?security-token=CAISvgJ1q6Ft5B2yfSjIr5b2Lo%2FEhrVV2vu6MEWF1nkEdsMfhLLKqDz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXnMQz3JqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYnegzlXUhv%2F64IClLcc%2BmqdsRIvJzWstJ7Gf9LWqChvSgk4TxhhcNFKSTQrInFCB0%2BcRObJl16iIMNtGfXtuMkagAGle5xT2tewhVclS52PQHV74iceX3r4mDTZgdDyxmyScUn49S3trQEJQCFDnqQ96lQKflCLKixHjT%2BLoLThoI9AK1bxZN3Xm%2BfZ63WgYESqKAtbW2lfx0yBLnmLK%2Fi6ov6UBCvOqsm817fH08gZq5PP9aDFoPU73zzDKcJmm1HvliAA&x-oss-access-key-id=STS.NUCe5pkjtm9Q2c42yQzL3kwaJ&x-oss-additional-headers=referer&x-oss-expires=1715323056&x-oss-process=image%2Fresize%2Cm_fill%2Ch_128%2Cw_128%2Climit_0&x-oss-signature=rQNDESDM2%2FS0FnvbnnGIz0L5mUCtnmFGU0PkqlE5e8o%3D&x-oss-signature-version=OSS2",
            "type": "file",
            "category": "image",
            "hidden": false,
            "status": "available",
            "url": "https://cn-beijing-data.aliyundrive.net/5hp3lmWZ%2F18654654%2F663dbb87a45ed768219f465a82ab7a4f9d337967%2F663dbb87fb1d942444cf44abb8e04eda74fc88e2?callback=eyJjYWxsYmFja1VybCI6Imh0dHA6Ly9iajI5LmFwaS1ocC5hbGl5dW5wZHMuY29tL3YyL2ZpbGUvZG93bmxvYWRfY2FsbGJhY2siLCJjYWxsYmFja0JvZHkiOiJodHRwSGVhZGVyLnJhbmdlPSR7aHR0cEhlYWRlci5yYW5nZX1cdTAwMjZidWNrZXQ9JHtidWNrZXR9XHUwMDI2b2JqZWN0PSR7b2JqZWN0fVx1MDAyNmRvbWFpbl9pZD0ke3g6ZG9tYWluX2lkfVx1MDAyNnVzZXJfaWQ9JHt4OnVzZXJfaWR9XHUwMDI2ZHJpdmVfaWQ9JHt4OmRyaXZlX2lkfVx1MDAyNmZpbGVfaWQ9JHt4OmZpbGVfaWR9XHUwMDI2cGRzX3BhcmFtcz0ke3g6cGRzX3BhcmFtc31cdTAwMjZ2ZXJzaW9uPSR7eDp2ZXJzaW9ufSIsImNhbGxiYWNrQm9keVR5cGUiOiJhcHBsaWNhdGlvbi94LXd3dy1mb3JtLXVybGVuY29kZWQiLCJjYWxsYmFja1N0YWdlIjoiYmVmb3JlLWV4ZWN1dGUiLCJjYWxsYmFja0ZhaWx1cmVBY3Rpb24iOiJpZ25vcmUifQ%3D%3D&callback-var=eyJ4OmRvbWFpbl9pZCI6ImJqMjkiLCJ4OnVzZXJfaWQiOiIwMDc1ODlkNzczMzk0ZGQxODdjMzk1ZWUzYzc3NDdiMCIsIng6ZHJpdmVfaWQiOiIxMzI5ODY1MCIsIng6ZmlsZV9pZCI6IjY2M2RiYjg3YTQ1ZWQ3NjgyMTlmNDY1YTgyYWI3YTRmOWQzMzc5NjciLCJ4OnBkc19wYXJhbXMiOiJ7XCJhcFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwifSIsIng6dmVyc2lvbiI6InYzIn0%3D&di=bj29&dr=18654654&f=663dbb87a45ed768219f465a82ab7a4f9d337967&pds-params=%7B%22ap%22%3A%2225dzX3vbYqktVxyX%22%7D&response-content-disposition=attachment%3B%20filename%2A%3DUTF-8%27%27&security-token=CAISvgJ1q6Ft5B2yfSjIr5b2Lo%2FEhrVV2vu6MEWF1nkEdsMfhLLKqDz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXnMQz3JqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYnegzlXUhv%2F64IClLcc%2BmqdsRIvJzWstJ7Gf9LWqChvSgk4TxhhcNFKSTQrInFCB0%2BcRObJl16iIMNtGfXtuMkagAGle5xT2tewhVclS52PQHV74iceX3r4mDTZgdDyxmyScUn49S3trQEJQCFDnqQ96lQKflCLKixHjT%2BLoLThoI9AK1bxZN3Xm%2BfZ63WgYESqKAtbW2lfx0yBLnmLK%2Fi6ov6UBCvOqsm817fH08gZq5PP9aDFoPU73zzDKcJmm1HvliAA&u=0075833233234dd187c395ee3c7747b0&x-oss-access-key-id=STS.NUCe5pkjtm9Q2c42yQzL3kwaJ&x-oss-additional-headers=referer&x-oss-expires=1715323056&x-oss-signature=LijNEL9pQVFfvvBbyddMy1BhtiSDTYcaQtWCJ7uXT5Q%3D&x-oss-signature-version=OSS2",
            "size": 85930,
            "starred": false,
            "parent_file_id": "recyclebin",
            "drive_id": "18654654",
            "file_id": "663dbb87a45ed768219f465a82ab7a4f9d337967",
            "file_extension": "png",
            "content_hash": "20FD6EEEE4D48EF4DF96B4DCF6D1F9A4EF029748",
            "content_hash_name": "sha1",
            "encrypt_mode": "none",
            "domain_id": "bj29",
            "created_at": "2024-05-10T06:15:35.761Z",
            "updated_at": "2024-05-10T06:21:46.850Z",
            "trashed_at": "2024-05-10T06:21:46.850Z",
            "punish_flag": 0,
            "image_media_metadata": {},
            "gmt_expired": "2024-05-20T06:21:46.748Z"
        },
        {
            "name": "mark (61).png",
            "thumbnail": "https://cn-beijing-data.aliyundrive.net/Ns57GI0J%2F18654654%2F663db23c8caba03781a540b9b0848e1434db38be%2F663db23cdbc03898404144efa3c6a32bd303f3fd?security-token=CAISvgJ1q6Ft5B2yfSjIr5b2Lo%2FEhrVV2vu6MEWF1nkEdsMfhLLKqDz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXnMQz3JqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYnegzlXUhv%2F64IClLcc%2BmqdsRIvJzWstJ7Gf9LWqChvSgk4TxhhcNFKSTQrInFCB0%2BcRObJl16iIMNtGfXtuMkagAGle5xT2tewhVclS52PQHV74iceX3r4mDTZgdDyxmyScUn49S3trQEJQCFDnqQ96lQKflCLKixHjT%2BLoLThoI9AK1bxZN3Xm%2BfZ63WgYESqKAtbW2lfx0yBLnmLK%2Fi6ov6UBCvOqsm817fH08gZq5PP9aDFoPU73zzDKcJmm1HvliAA&x-oss-access-key-id=STS.NUCe5pkjtm9Q2c42yQzL3kwaJ&x-oss-additional-headers=referer&x-oss-expires=1715323056&x-oss-process=image%2Fresize%2Cm_fill%2Ch_128%2Cw_128%2Climit_0&x-oss-signature=mCygm28h%2FflCdSws8z2ffGpPt8kvwcEVqHzWRC80WDE%3D&x-oss-signature-version=OSS2",
            "type": "file",
            "category": "image",
            "hidden": false,
            "status": "available",
            "url": "https://cn-beijing-data.aliyundrive.net/Ns57GI0J%2F18654654%2F663db23c8caba03781a540b9b0848e1434db38be%2F663db23cdbc03898404144efa3c6a32bd303f3fd?callback=eyJjYWxsYmFja1VybCI6Imh0dHA6Ly9iajI5LmFwaS1ocC5hbGl5dW5wZHMuY29tL3YyL2ZpbGUvZG93bmxvYWRfY2FsbGJhY2siLCJjYWxsYmFja0JvZHkiOiJodHRwSGVhZGVyLnJhbmdlPSR7aHR0cEhlYWRlci5yYW5nZX1cdTAwMjZidWNrZXQ9JHtidWNrZXR9XHUwMDI2b2JqZWN0PSR7b2JqZWN0fVx1MDAyNmRvbWFpbl9pZD0ke3g6ZG9tYWluX2lkfVx1MDAyNnVzZXJfaWQ9JHt4OnVzZXJfaWR9XHUwMDI2ZHJpdmVfaWQ9JHt4OmRyaXZlX2lkfVx1MDAyNmZpbGVfaWQ9JHt4OmZpbGVfaWR9XHUwMDI2cGRzX3BhcmFtcz0ke3g6cGRzX3BhcmFtc31cdTAwMjZ2ZXJzaW9uPSR7eDp2ZXJzaW9ufSIsImNhbGxiYWNrQm9keVR5cGUiOiJhcHBsaWNhdGlvbi94LXd3dy1mb3JtLXVybGVuY29kZWQiLCJjYWxsYmFja1N0YWdlIjoiYmVmb3JlLWV4ZWN1dGUiLCJjYWxsYmFja0ZhaWx1cmVBY3Rpb24iOiJpZ25vcmUifQ%3D%3D&callback-var=eyJ4OmRvbWFpbl9pZCI6ImJqMjkiLCJ4OnVzZXJfaWQiOiIwMDc1ODlkNzczMzk0ZGQxODdjMzk1ZWUzYzc3NDdiMCIsIng6ZHJpdmVfaWQiOiIxMzI5ODY1MCIsIng6ZmlsZV9pZCI6IjY2M2RiMjNjOGNhYmEwMzc4MWE1NDBiOWIwODQ4ZTE0MzRkYjM4YmUiLCJ4OnBkc19wYXJhbXMiOiJ7XCJhcFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwifSIsIng6dmVyc2lvbiI6InYzIn0%3D&di=bj29&dr=18654654&f=663db23c8caba03781a540b9b0848e1434db38be&pds-params=%7B%22ap%22%3A%2225dzX3vbYqktVxyX%22%7D&response-content-disposition=attachment%3B%20filename%2A%3DUTF-8%27%27&security-token=CAISvgJ1q6Ft5B2yfSjIr5b2Lo%2FEhrVV2vu6MEWF1nkEdsMfhLLKqDz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXnMQz3JqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYnegzlXUhv%2F64IClLcc%2BmqdsRIvJzWstJ7Gf9LWqChvSgk4TxhhcNFKSTQrInFCB0%2BcRObJl16iIMNtGfXtuMkagAGle5xT2tewhVclS52PQHV74iceX3r4mDTZgdDyxmyScUn49S3trQEJQCFDnqQ96lQKflCLKixHjT%2BLoLThoI9AK1bxZN3Xm%2BfZ63WgYESqKAtbW2lfx0yBLnmLK%2Fi6ov6UBCvOqsm817fH08gZq5PP9aDFoPU73zzDKcJmm1HvliAA&u=0075833233234dd187c395ee3c7747b0&x-oss-access-key-id=STS.NUCe5pkjtm9Q2c42yQzL3kwaJ&x-oss-additional-headers=referer&x-oss-expires=1715323056&x-oss-signature=MPuX8r4vdTlOGngPGLD9m9dZK%2BcGxWeuNmxKQWGzC6E%3D&x-oss-signature-version=OSS2",
            "size": 64497,
            "starred": false,
            "parent_file_id": "recyclebin",
            "drive_id": "18654654",
            "file_id": "663db23c8caba03781a540b9b0848e1434db38be",
            "file_extension": "png",
            "content_hash": "E4F31A4544341A0C7039EE0E19F2EF48118E5BD6",
            "content_hash_name": "sha1",
            "encrypt_mode": "none",
            "domain_id": "bj29",
            "created_at": "2024-05-10T05:35:56.900Z",
            "updated_at": "2024-05-10T05:37:18.746Z",
            "trashed_at": "2024-05-10T05:37:18.746Z",
            "punish_flag": 0,
            "image_media_metadata": {},
            "gmt_expired": "2024-05-20T05:37:18.684Z"
        },
        {
            "name": "QQ截图20220817160538.png",
            "thumbnail": "https://cn-beijing-data.aliyundrive.net/oNIBeSCk%2F18654654%2F6341189d4fa988adf1dc4bacbc5f04326ef4e3d1%2F6341189dd7191db9b06d47f3bc1e40110a254841?security-token=CAISvgJ1q6Ft5B2yfSjIr5b2Lo%2FEhrVV2vu6MEWF1nkEdsMfhLLKqDz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXnMQz3JqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYnegzlXUhv%2F64IClLcc%2BmqdsRIvJzWstJ7Gf9LWqChvSgk4TxhhcNFKSTQrInFCB0%2BcRObJl16iIMNtGfXtuMkagAGle5xT2tewhVclS52PQHV74iceX3r4mDTZgdDyxmyScUn49S3trQEJQCFDnqQ96lQKflCLKixHjT%2BLoLThoI9AK1bxZN3Xm%2BfZ63WgYESqKAtbW2lfx0yBLnmLK%2Fi6ov6UBCvOqsm817fH08gZq5PP9aDFoPU73zzDKcJmm1HvliAA&x-oss-access-key-id=STS.NUCe5pkjtm9Q2c42yQzL3kwaJ&x-oss-additional-headers=referer&x-oss-expires=1715323056&x-oss-process=image%2Fresize%2Cm_fill%2Ch_128%2Cw_128%2Climit_0&x-oss-signature=nlRxLWOMi%2BrCGoiaNxjVBSYJXvhKDdXwP5JlQYs9xzs%3D&x-oss-signature-version=OSS2",
            "type": "file",
            "category": "image",
            "hidden": false,
            "status": "available",
            "url": "https://cn-beijing-data.aliyundrive.net/oNIBeSCk%2F18654654%2F6341189d4fa988adf1dc4bacbc5f04326ef4e3d1%2F6341189dd7191db9b06d47f3bc1e40110a254841?callback=eyJjYWxsYmFja1VybCI6Imh0dHA6Ly9iajI5LmFwaS1ocC5hbGl5dW5wZHMuY29tL3YyL2ZpbGUvZG93bmxvYWRfY2FsbGJhY2siLCJjYWxsYmFja0JvZHkiOiJodHRwSGVhZGVyLnJhbmdlPSR7aHR0cEhlYWRlci5yYW5nZX1cdTAwMjZidWNrZXQ9JHtidWNrZXR9XHUwMDI2b2JqZWN0PSR7b2JqZWN0fVx1MDAyNmRvbWFpbl9pZD0ke3g6ZG9tYWluX2lkfVx1MDAyNnVzZXJfaWQ9JHt4OnVzZXJfaWR9XHUwMDI2ZHJpdmVfaWQ9JHt4OmRyaXZlX2lkfVx1MDAyNmZpbGVfaWQ9JHt4OmZpbGVfaWR9XHUwMDI2cGRzX3BhcmFtcz0ke3g6cGRzX3BhcmFtc31cdTAwMjZ2ZXJzaW9uPSR7eDp2ZXJzaW9ufSIsImNhbGxiYWNrQm9keVR5cGUiOiJhcHBsaWNhdGlvbi94LXd3dy1mb3JtLXVybGVuY29kZWQiLCJjYWxsYmFja1N0YWdlIjoiYmVmb3JlLWV4ZWN1dGUiLCJjYWxsYmFja0ZhaWx1cmVBY3Rpb24iOiJpZ25vcmUifQ%3D%3D&callback-var=eyJ4OmRvbWFpbl9pZCI6ImJqMjkiLCJ4OnVzZXJfaWQiOiIwMDc1ODlkNzczMzk0ZGQxODdjMzk1ZWUzYzc3NDdiMCIsIng6ZHJpdmVfaWQiOiIxMzI5ODY1MCIsIng6ZmlsZV9pZCI6IjYzNDExODlkNGZhOTg4YWRmMWRjNGJhY2JjNWYwNDMyNmVmNGUzZDEiLCJ4OnBkc19wYXJhbXMiOiJ7XCJhcFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwifSIsIng6dmVyc2lvbiI6InYzIn0%3D&di=bj29&dr=18654654&f=6341189d4fa988adf1dc4bacbc5f04326ef4e3d1&pds-params=%7B%22ap%22%3A%2225dzX3vbYqktVxyX%22%7D&response-content-disposition=attachment%3B%20filename%2A%3DUTF-8%27%27&security-token=CAISvgJ1q6Ft5B2yfSjIr5b2Lo%2FEhrVV2vu6MEWF1nkEdsMfhLLKqDz2IHhMf3NpBOkZvvQ1lGlU6%2Fcalq5rR4QAXlDfNXnMQz3JqFHPWZHInuDox55m4cTXNAr%2BIhr%2F29CoEIedZdjBe%2FCrRknZnytou9XTfimjWFrXWv%2Fgy%2BQQDLItUxK%2FcCBNCfpPOwJms7V6D3bKMuu3OROY6Qi5TmgQ41Uh1jgjtPzkkpfFtkGF1GeXkLFF%2B97DRbG%2FdNRpMZtFVNO44fd7bKKp0lQLs0ARrv4r1fMUqW2X543AUgFLhy2KKMPY99xpFgh9a7j0iCbSGyUu%2FhcRm5sw9%2Byfo34lVYnegzlXUhv%2F64IClLcc%2BmqdsRIvJzWstJ7Gf9LWqChvSgk4TxhhcNFKSTQrInFCB0%2BcRObJl16iIMNtGfXtuMkagAGle5xT2tewhVclS52PQHV74iceX3r4mDTZgdDyxmyScUn49S3trQEJQCFDnqQ96lQKflCLKixHjT%2BLoLThoI9AK1bxZN3Xm%2BfZ63WgYESqKAtbW2lfx0yBLnmLK%2Fi6ov6UBCvOqsm817fH08gZq5PP9aDFoPU73zzDKcJmm1HvliAA&u=0075833233234dd187c395ee3c7747b0&x-oss-access-key-id=STS.NUCe5pkjtm9Q2c42yQzL3kwaJ&x-oss-additional-headers=referer&x-oss-expires=1715323056&x-oss-signature=kxN5EFa%2FIHHaMhqQwWUT3awY4wYzmS9NYuNGTwB%2FAgA%3D&x-oss-signature-version=OSS2",
            "size": 4485,
            "starred": false,
            "parent_file_id": "recyclebin",
            "drive_id": "18654654",
            "file_id": "6341189d4fa988adf1dc4bacbc5f04326ef4e3d1",
            "file_extension": "png",
            "content_hash": "1BBD89BF706363CBF61B81A77490742149657D8A",
            "content_hash_name": "sha1",
            "encrypt_mode": "none",
            "domain_id": "bj29",
            "created_at": "2022-10-08T06:28:45.646Z",
            "updated_at": "2024-05-10T01:22:00.691Z",
            "trashed_at": "2024-05-10T01:22:00.691Z",
            "punish_flag": 0,
            "image_media_metadata": {},
            "gmt_expired": "2024-05-20T01:22:00.639Z"
        }
    ],
    "next_marker": ""
}
* */

/**
* 回收站响应信息
*
* @author weiba
* @date 2024/5/10 14:26
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetRecycleResponse {

    /**
     * items
     */
    @JSONField(name = "items")
    private List<ItemsResponse> items;
    /**
     * nextMarker
     */
    @JSONField(name = "next_marker")
    private String nextMarker;

    /**
     * ItemsResponse
     */
    @NoArgsConstructor
    @Data
    public static class ItemsResponse {
        /**
         * name
         */
        @JSONField(name = "name")
        private String name;
        /**
         * type
         */
        @JSONField(name = "type")
        private String type;
        /**
         * category
         */
        @JSONField(name = "category")
        private String category;
        /**
         * hidden
         */
        @JSONField(name = "hidden")
        private Boolean hidden;
        /**
         * status
         */
        @JSONField(name = "status")
        private String status;
        /**
         * url
         */
        @JSONField(name = "url")
        private String url;
        /**
         * size
         */
        @JSONField(name = "size")
        private Integer size;
        /**
         * starred
         */
        @JSONField(name = "starred")
        private Boolean starred;
        /**
         * parentFileId
         */
        @JSONField(name = "parent_file_id")
        private String parentFileId;
        /**
         * driveId
         */
        @JSONField(name = "drive_id")
        private String driveId;
        /**
         * fileId
         */
        @JSONField(name = "file_id")
        private String fileId;
        /**
         * fileExtension
         */
        @JSONField(name = "file_extension")
        private String fileExtension;
        /**
         * contentHash
         */
        @JSONField(name = "content_hash")
        private String contentHash;
        /**
         * contentHashName
         */
        @JSONField(name = "content_hash_name")
        private String contentHashName;
        /**
         * encryptMode
         */
        @JSONField(name = "encrypt_mode")
        private String encryptMode;
        /**
         * domainId
         */
        @JSONField(name = "domain_id")
        private String domainId;
        /**
         * createdAt
         */
        @JSONField(name = "created_at")
        private String createdAt;
        /**
         * updatedAt
         */
        @JSONField(name = "updated_at")
        private String updatedAt;
        /**
         * trashedAt
         */
        @JSONField(name = "trashed_at")
        private String trashedAt;
        /**
         * punishFlag
         */
        @JSONField(name = "punish_flag")
        private Integer punishFlag;
        /**
         * gmtExpired
         */
        @JSONField(name = "gmt_expired")
        private String gmtExpired;
        /**
         * thumbnail
         */
        @JSONField(name = "thumbnail")
        private String thumbnail;
        /**
         * imageMediaMetadata
         */
        @JSONField(name = "image_media_metadata")
        private ImageMediaMetadataResponse imageMediaMetadata;

        /**
         * ImageMediaMetadataResponse
         */
        @NoArgsConstructor
        @Data
        public static class ImageMediaMetadataResponse {
        }
    }
}
