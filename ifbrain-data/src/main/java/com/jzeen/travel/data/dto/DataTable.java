package com.jzeen.travel.data.dto;

import java.io.Serializable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.jzeen.travel.data.repository.AdvancedJpaRepository;
import com.mysema.query.types.Predicate;

/**
 * 用于DataTables组件的数据分页表格。
 *
 * @param <T>
 *            实体类型。
 * @param <ID>
 *            主键类型。
 */
public class DataTable<T, ID extends Serializable>
{
    /**
     * The draw counter that this object is a response to.
     */
    private int draw;

    /**
     * Total records, before filtering.
     */
    private int recordsTotal;

    /**
     * Total records, after filtering.
     */
    private int recordsFiltered;

    /**
     * The data to be displayed in the table.
     */
    private Iterable<T> data;

    private String error;

    public int getDraw()
    {
        return draw;
    }

    public void setDraw(int draw)
    {
        this.draw = draw;
    }

    public int getRecordsTotal()
    {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal)
    {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered()
    {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered)
    {
        this.recordsFiltered = recordsFiltered;
    }

    public Iterable<T> getData()
    {
        return data;
    }

    public void setData(Iterable<T> data)
    {
        this.data = data;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    /**
     * 从HTTP请求中构建分页表格。
     * 
     * @param request
     *            HTTP请求。
     * @param repository
     *            仓储类。
     * @param predicate
     *            查询谓词。
     * @return 分页表格。
     */
    public static <T, ID extends Serializable> DataTable<T, ID> fromRequest(HttpServletRequest request,
            AdvancedJpaRepository<T, ID> repository, Predicate predicate)
    {
        int draw = Integer.parseInt(request.getParameter("draw"));
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        String orderColumnIndex = request.getParameter("order[0][column]");
        String orderColumnName = request.getParameter("columns[" + orderColumnIndex + "][data]");
        String orderDir = request.getParameter("order[0][dir]");
        Direction direction = Direction.fromString(orderDir.toUpperCase());

        DataTable<T, ID> dataTable = new DataTable<T, ID>();
        dataTable.setDraw(draw);
        if (length == -1)
        {
            Iterable<T> data = repository.findAll(predicate);
            int count = 0;
            Iterator<T> iterator = data.iterator();
            while (iterator.hasNext())
            {
                iterator.next();
                ++count;
            }
            dataTable.setRecordsTotal(count);
            dataTable.setRecordsFiltered(count);
            dataTable.setData(data);
        }
        else
        {
            int page = start / length;
            int size = length;
            Pageable pageable = new PageRequest(page, size, direction, orderColumnName);
            Page<T> entitys = repository.findAll(predicate, pageable);

            int totalCount = (int) entitys.getTotalElements();

            dataTable.setRecordsTotal(totalCount);
            dataTable.setRecordsFiltered(totalCount);
            dataTable.setData(entitys.getContent());
        }

        return dataTable;
    }

    /**
     * 从请求中构建分页表格。
     * 
     * @param request
     *            HTTP请求。
     * @param repository
     *            仓储类。
     * @return 分页表格。
     */
    public static <T, ID extends Serializable> DataTable<T, ID> fromRequest(HttpServletRequest request,
            AdvancedJpaRepository<T, ID> repository)
    {
        return fromRequest(request, repository, null);
    }
}
