package Classes.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class Storage {
        private List<Rack> racks;

        public Storage() {
                this.racks = new ArrayList<>();
        }

        public void addRack(Rack rack) {
                this.racks.add(rack);
        }

        public Rack getRackById(String idRack) {
                for (Rack r : racks) {
                        if (r.getIdRack().equals(idRack)) return r;
                }
                return null;
        }

        public List<Rack> getRacks() {
                return racks;
        }
}
