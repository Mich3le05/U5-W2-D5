package it.epicode.gestione_viaggi.cloudinary;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class CloudinaryController {

    private final Cloudinary cloudinary;

    @PostMapping(path = "/uploadme", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(
            @RequestPart("file")
            MultipartFile file) {

        try {
            Map<String, Object> result = cloudinary.uploader()
                    .upload(file.getBytes(), Cloudinary.asMap("folder", "WorkerAvatar", "public_id", file.getOriginalFilename()));
            return result.get("secure_url").toString();  // Restituisce l'URL sicuro dell'immagine caricata
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento del file su Cloudinary", e);
        }
    }
}
