package servidor.model;

public enum Command {
    CadPiloto, CadCarro, CadParticipante, CadEquipe, 
    RemPiloto, RemCarro, RemParticipante, RemEquipe,
    EditPiloto, EditCarro, EditParticipante, EditEquipe,
    EditCorrida,
    ComecarCorrida, PararCorrida,
}
