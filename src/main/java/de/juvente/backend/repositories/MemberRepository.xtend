package de.juvente.backend.repositories

import de.juvente.backend.data.Member
import de.juvente.backend.security.JuventeAccessSecuredRepository

interface MemberRepository extends JuventeAccessSecuredRepository<Member, String> {
	
}
