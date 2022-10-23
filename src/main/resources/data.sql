
insert into role values (1, 'ROLE_USER') ON CONFLICT DO NOTHING;
insert into role values (2, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;
insert into role values (3, 'ROLE_CREATOR') ON CONFLICT DO NOTHING;
insert into role values (4, 'ROLE_LEAD') ON CONFLICT DO NOTHING;
insert into role values (5, 'ROLE_COLLABORATOR') ON CONFLICT DO NOTHING;
insert into role values (6, 'ROLE_VISITOR') ON CONFLICT DO NOTHING;

