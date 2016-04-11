select *
from Software s, Location loc, Located_in loc_in, Requests r
where s.id_software = loc_in.soft_id and loc.id_location = loc_in.loc_id and r.id_request = loc_in.req_id