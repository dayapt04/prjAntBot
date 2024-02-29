package BusinessLogic;

import java.util.List;
import DataAccess.LZHormigaDAO;
import DataAccess.DTO.LZHormigaDTO;

public class LZHormigaBL {
    private LZHormigaDAO lzhormigaDAO = new LZHormigaDAO();

    public LZHormigaBL() {}

    public List<LZHormigaDTO> getAll() throws Exception {
        return lzhormigaDAO.readAll();
    }

    public LZHormigaDTO getByIdHormiga(int idHormiga) throws Exception {
        return lzhormigaDAO.readBy(idHormiga);
    }

    public boolean create(LZHormigaDTO lzhormigaDTO) throws Exception {   
        return lzhormigaDAO.create(lzhormigaDTO);
    }

    public boolean update(LZHormigaDTO lzhormigaDTO) throws Exception {
        return lzhormigaDAO.update(lzhormigaDTO);
    }

    public boolean delete(int idHormiga) throws Exception {
        return lzhormigaDAO.delete(idHormiga);
    }

    public Integer getMaxRow() throws Exception {
        return lzhormigaDAO.getMaxRow();
    }
}
