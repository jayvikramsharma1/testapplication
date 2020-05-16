package in.bank.components;

import in.bank.exception.NoBranchFoundByIdException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HeadOffice {
    List<Branch> branches = new LinkedList<>();

    public Branch getBranchById(String branchId) throws NoBranchFoundByIdException {
        List<Branch> filterdBranch = branches.stream().filter(br -> br.getBranchId().equalsIgnoreCase(branchId)).collect(Collectors.toList());
        if(!filterdBranch.isEmpty()) {
            return filterdBranch.get(0);
        } else {
            throw new NoBranchFoundByIdException();
        }
    }

    public Branch createBranch() {
        Branch branch = new Branch(String.valueOf(this.branches.size() + 1));
        branches.add(branch);
        return branch;
    }

    public List<Branch> getAllBranches() {
        return this.branches;
    }


}
