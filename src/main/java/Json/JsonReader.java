package Json;

import Entity.Resume;
import com.google.gson.*;

import java.util.ArrayList;

public class JsonReader {

    public ArrayList<Resume> getResumes(String json) {
        ArrayList<Resume> resumes = new ArrayList<>();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray array = jsonObject.getAsJsonArray("objects");
        for (int i = 0; i < array.size(); i++) {
            Resume resume = new Resume();

            JsonObject currentJsonResume = array.get(i).getAsJsonObject();
            try {

            } catch (JsonParseException e) {
                System.err.println(e);
            }

            if (!currentJsonResume.get("payment_from").isJsonNull()) {
                resume.setPaymentFrom(currentJsonResume.get("payment_from").getAsString());
            }
            if (!currentJsonResume.get("payment_to").isJsonNull()) {
                resume.setPaymentTo(currentJsonResume.get("payment_to").getAsString());
            }
            if (!currentJsonResume.get("phone").isJsonNull()) {
                resume.setPhone(currentJsonResume.get("phone").getAsString());
            }
            if (!currentJsonResume.get("currency").isJsonNull()) {
                resume.setCurrency(currentJsonResume.get("currency").getAsString());
            }
            if (!currentJsonResume.get("profession").isJsonNull()) {
                resume.setProfession(currentJsonResume.get("profession").getAsString());
            }
            if (!currentJsonResume.get("compensation").isJsonNull()) {
                resume.setCompensation(currentJsonResume.get("compensation").getAsString());
            }

            JsonArray metroArray = currentJsonResume.getAsJsonArray("metro");
            if (metroArray != null) {
                for (int a = 0; a < metroArray.size(); a++) {
                    resume.addMetro(metroArray.get(a).toString());
                }
            }

            if (!currentJsonResume.get("candidat").isJsonNull()) {
                resume.setCandidat(currentJsonResume.get("candidat").getAsString());
            }
            if (!currentJsonResume.get("type_of_work").isJsonNull()) {
                resume.setTypeOfWork(currentJsonResume.get("type_of_work").getAsJsonObject().get("title").getAsString());
            }
            if (!currentJsonResume.get("education").isJsonNull()) {
                resume.setEducation(currentJsonResume.get("education").getAsJsonObject().get("title").getAsString());
            }
            if (!currentJsonResume.get("experience").isJsonNull()) {
                resume.setExperience(currentJsonResume.get("experience").getAsJsonObject().get("title").getAsString());
            }
            if (!currentJsonResume.get("town").isJsonNull()) {
                resume.setTown(currentJsonResume.get("town").getAsJsonObject().get("title").getAsString());
            }

            resumes.add(resume);
        }
        return resumes;
    }
}
