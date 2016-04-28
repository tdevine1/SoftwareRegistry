select *
from Software s, Location loc, Located_in loc_in
where s.id_software = loc_in.soft_id and loc.id_location = loc_in.loc_id;