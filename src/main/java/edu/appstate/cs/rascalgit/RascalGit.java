package edu.appstate.cs.rascalgit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.rascalmpl.exceptions.RuntimeExceptionFactory;

import io.usethesource.vallang.IList;
import io.usethesource.vallang.IListWriter;
import io.usethesource.vallang.ISourceLocation;
import io.usethesource.vallang.IString;
import io.usethesource.vallang.IValueFactory;

public class RascalGit {
	private final IValueFactory values;
	private HashMap<ISourceLocation, Repository> repoMap = new HashMap<>();
    
	public RascalGit(IValueFactory values) {
		super();
		this.values = values;
	}
	
	public void cloneRemoteRepository(IString remotePath, ISourceLocation localPath) {
        if (!repoMap.containsKey(localPath)) {
            String origin = remotePath.getValue();
            File localDir = new File(localPath.getPath());
            
            try {
                Git git = Git.cloneRepository()
                    .setURI(origin)
                    .setDirectory(localDir)
                    .call();
                Repository repo = git.getRepository();
                repoMap.put(localPath, repo);
            } catch (InvalidRemoteException e) {
                throw RuntimeExceptionFactory.javaException(e, null, null);
            } catch (TransportException e) {
                throw RuntimeExceptionFactory.javaException(e, null, null);
            } catch (GitAPIException e) {
                throw RuntimeExceptionFactory.javaException(e, null, null);
            }		    
        }
	}
	
    public IList getTags(ISourceLocation localPath) {
        if (repoMap.containsKey(localPath)) {
            Repository repo = repoMap.get(localPath);
            List<Ref> tagRefs;
            try {
                tagRefs = repo.getRefDatabase().getRefsByPrefix(Constants.R_TAGS);
            } catch (IOException e) {
                throw RuntimeExceptionFactory.javaException(e, null, null);
            }

            IListWriter lw = values.listWriter();
            for (Ref r : tagRefs) {
                lw.append(values.string(r.getName().substring(Constants.R_TAGS.length())));
            }
            return lw.done();
        }
        return values.listWriter().done();
    }

	public void openLocalRepository(ISourceLocation loc) {
        if (!repoMap.containsKey(loc)) {
            File repoDir = new File(loc.getPath());
            try {
                Git git = Git.open(repoDir);
                Repository repo = git.getRepository();
                repoMap.put(loc, repo);
            } catch (IOException ioe) {
                throw RuntimeExceptionFactory.javaException(ioe, null, null);
            }    
        }
	}

    public void switchToTag(ISourceLocation loc, IString tag) {
        if (repoMap.containsKey(loc)) {
            File repoDir = new File(loc.getPath());
            try {
                Git git = Git.open(repoDir);
                String repoTag = Constants.R_TAGS + tag.getValue();
                git.checkout().setName(repoTag).call();
            } catch (GitAPIException ge) {
                throw RuntimeExceptionFactory.javaException(ge, null, null);
            } catch (IOException ioe) {
                throw RuntimeExceptionFactory.javaException(ioe, null, null);
            }    
        }
    }
	
}
